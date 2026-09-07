#!/usr/bin/env python3
import sys
import os
import re
import json
import urllib.request
import urllib.error
import argparse

def parse_recipe_filename(filepath):
    """Extracts package name and version from standard Yocto python3-*.bb filenames."""
    basename = os.path.basename(filepath)
    match = re.match(r'python3-(.+)_([0-9a-zA-Z\.\-]+)\.bb$', basename)
    if not match:
        print(f"Error: Filename '{basename}' does not match standard Yocto 'python3-<name>_<version>.bb' format.")
        sys.exit(1)
    return match.group(1), match.group(2)

def clean_bb_content(content):
    """Removes comments and collapses multi-line variable definitions."""
    # Remove full line comments
    content = re.sub(r'^\s*#.*$', '', content, flags=re.MULTILINE)
    # Collapse line continuations (backslash followed by newline)
    content = re.sub(r'\\\n\s*', ' ', content)
    return content

def extract_recipe_variables(content):
    """Extracts RDEPENDS:${PN} and PYPI_PACKAGE from the recipe content."""
    variables = {
        'RDEPENDS': set(),
        'PYPI_PACKAGE': None
    }
    
    # Match RDEPENDS:${PN} = "..." or += "..."
    rdepends_match = re.search(r'RDEPENDS:\$\{PN\}\s*\+?=\s*"([^"]*)"', content)
    if rdepends_match:
        # Split by whitespace to get individual Yocto packages
        variables['RDEPENDS'] = set(rdepends_match.group(1).split())
        
    pypi_pkg_match = re.search(r'PYPI_PACKAGE\s*=\s*"([^"]*)"', content)
    if pypi_pkg_match:
        variables['PYPI_PACKAGE'] = pypi_pkg_match.group(1)
        
    return variables

def fetch_pypi_requires_dist(pkg_name, version):
    """Fetches dependency info from PyPI API."""
    url = f"https://pypi.org/pypi/{pkg_name}/{version}/json"
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Yocto-Recipe-Checker/1.0'})
        with urllib.request.urlopen(req) as response:
            data = json.loads(response.read().decode('utf-8'))
            return data.get('info', {}).get('requires_dist') or []
    except urllib.error.HTTPError as e:
        if e.code == 404:
            print(f"Error: Could not find package '{pkg_name}' version '{version}' on PyPI.")
            print("Note: If the PyPI name differs from the recipe name, ensure PYPI_PACKAGE is set in the recipe.")
            sys.exit(1)
        else:
            print(f"HTTP Error {e.code} when contacting PyPI.")
            sys.exit(1)
    except Exception as e:
        print(f"Error fetching from PyPI: {e}")
        sys.exit(1)

def pypi_req_to_yocto_pkg(req_string):
    """Converts a PyPI requirement string to a Yocto python3-* package name."""
    # Ignore conditional dependencies tied to "extras" (e.g., testing, docs)
    if 'extra ==' in req_string or 'extra==' in req_string:
        return None
        
    # Extract the base package name (ignore version constraints and environment markers)
    # E.g., "charset-normalizer (<4,>=2) ; python_version >= '3'" -> "charset-normalizer"
    match = re.match(r'^([a-zA-Z0-9_\-\.]+)', req_string.strip())
    if match:
        raw_name = match.group(1)
        # Standard Yocto conversion: lower case, replace underscores with dashes
        clean_name = raw_name.lower().replace('_', '-')
        return f"python3-{clean_name}"
    return None

def main():
    parser = argparse.ArgumentParser(description="Validate RDEPENDS in a Yocto Python3 recipe against PyPI.")
    parser.add_argument("recipe", help="Path to the python3-*.bb recipe file")
    args = parser.parse_args()

    if not os.path.exists(args.recipe):
        print(f"Error: File '{args.recipe}' not found.")
        sys.exit(1)

    # 1. Parse Name and Version
    bb_name, bb_version = parse_recipe_filename(args.recipe)
    
    with open(args.recipe, 'r') as f:
        raw_content = f.read()

    # 2. Extract Variables
    clean_content = clean_bb_content(raw_content)
    vars = extract_recipe_variables(clean_content)
    
    # Use PYPI_PACKAGE if defined, otherwise default to the parsed filename name
    pypi_name = vars['PYPI_PACKAGE'] if vars['PYPI_PACKAGE'] else bb_name
    yocto_rdepends = vars['RDEPENDS']

    print(f"Analyzing Recipe: {os.path.basename(args.recipe)}")
    print(f"  Target PyPI Package: {pypi_name}=={bb_version}")
    
    # 3. Fetch PyPI requirements
    pypi_requires = fetch_pypi_requires_dist(pypi_name, bb_version)
    
    # 4. Map PyPI requirements to Yocto package names
    expected_rdepends = set()
    for req in pypi_requires:
        yocto_pkg = pypi_req_to_yocto_pkg(req)
        if yocto_pkg:
            expected_rdepends.add(yocto_pkg)

    # 5. Compare and Report
    print("\n--- Validation Results ---")
    missing_in_recipe = expected_rdepends - yocto_rdepends
    extra_in_recipe = yocto_rdepends - expected_rdepends
    
    if not missing_in_recipe and not extra_in_recipe:
        print("✅ RDEPENDS:${PN} matches PyPI requirements exactly.")
    else:
        if missing_in_recipe:
            print("❌ Missing in recipe RDEPENDS:${PN}:")
            for pkg in sorted(missing_in_recipe):
                print(f"    + {pkg}")
                
        if extra_in_recipe:
            print("⚠️ Extra in recipe RDEPENDS:${PN} (not required by PyPI base requirements):")
            for pkg in sorted(extra_in_recipe):
                print(f"    - {pkg}")
                
        print("\nSuggested RDEPENDS:${PN} line:")
        suggested_line = ' \\\n    '.join(sorted(expected_rdepends))
        print(f'RDEPENDS:${{PN}} = "\\\n    {suggested_line} \\\n"')

if __name__ == "__main__":
    main()
