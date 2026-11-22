import sys
import os
import re
import json

try:
    from packaging.version import parse as parse_version
except ImportError:
    try:
        from pip._vendor.packaging.version import parse as parse_version
    except ImportError:
        def parse_version(v):
            return [int(x) if x.isdigit() else x for x in re.split(r'(\d+)', v) if x]

def safe_parse_version(v_str):
    try:
        return parse_version(v_str)
    except Exception:
        return [int(x) if x.isdigit() else x for x in re.split(r'(\d+)', v_str) if x]

def normalize_name(name):
    return re.sub(r"[-_.]+", "-", name).strip().lower()

recipes_dir = sys.argv[1] if len(sys.argv) > 1 else "../recipes-python"
resolution_file = sys.argv[2] if len(sys.argv) > 2 else "resolved-dependencies.json"
resolved_txt_file = sys.argv[3] if len(sys.argv) > 3 else "resolved-requirements.txt"

with open(resolution_file, "r", encoding="utf-8") as f:
    report = json.load(f)

resolved_pkgs = {}
resolved_list = []

for item in report.get("install", []):
    metadata = item.get("metadata", {})
    name = metadata.get("name")
    version = metadata.get("version")
    if name and version:
        resolved_pkgs[normalize_name(name)] = (name, version)
        resolved_list.append((name, version))

# Save resolved-requirements.txt
with open(resolved_txt_file, "w", encoding="utf-8") as f:
    for name, version in sorted(resolved_list, key=lambda x: x[0].lower()):
        f.write(f"{name}=={version}\n")

print(f"\nSaved dependency resolution report to '{resolution_file}' and '{resolved_txt_file}'.\n")

# Scan recipes-python
recipes = []
for root, _, files in os.walk(recipes_dir):
    for f in sorted(files):
        if not f.endswith(".bb"):
            continue
        bb_path = os.path.join(root, f)
        base_name = f

        m = re.match(r"python3-(.+)_([0-9a-zA-Z\.\-+~]+)\.bb$", base_name)
        if not m:
            continue
        file_pkg, recipe_ver = m.group(1), m.group(2)

        rel_dir = os.path.relpath(root, recipes_dir)
        dir_name = rel_dir.split(os.sep)[0] if rel_dir != "." else file_pkg

        pypi_pkg = None
        try:
            with open(bb_path, "r", encoding="utf-8", errors="ignore") as r_file:
                content = r_file.read()
                pypi_match = re.search(r'PYPI_PACKAGE\s*=\s*["\']([^"\']+)["\']', content)
                if pypi_match:
                    pypi_pkg = pypi_match.group(1)
        except Exception:
            pass

        candidates = [normalize_name(c) for c in (dir_name, file_pkg, pypi_pkg) if c]
        recipes.append({
            "bb_path": bb_path,
            "dir_name": dir_name,
            "recipe_file": base_name,
            "recipe_ver": recipe_ver,
            "candidates": candidates,
        })

updates_needed = []
up_to_date = []
downgrades = []
unresolved = []

for r in recipes:
    resolved_info = None
    for cand in r["candidates"]:
        if cand in resolved_pkgs:
            resolved_info = resolved_pkgs[cand]
            break

    if not resolved_info:
        unresolved.append(r)
        continue

    res_name, res_ver = resolved_info
    r["resolved_name"] = res_name
    r["resolved_ver"] = res_ver

    cur_v = safe_parse_version(r["recipe_ver"])
    res_v = safe_parse_version(res_ver)

    if res_v > cur_v:
        updates_needed.append(r)
    elif res_v == cur_v:
        up_to_date.append(r)
    else:
        downgrades.append(r)

updates_needed.sort(key=lambda x: x["dir_name"].lower())
up_to_date.sort(key=lambda x: x["dir_name"].lower())
downgrades.sort(key=lambda x: x["dir_name"].lower())
unresolved.sort(key=lambda x: x["dir_name"].lower())

recipe_candidate_set = set()
for r in recipes:
    recipe_candidate_set.update(r["candidates"])

transitive_pkgs = [
    (orig_name, ver)
    for norm_name, (orig_name, ver) in resolved_pkgs.items()
    if norm_name not in recipe_candidate_set
]

print("=" * 80)
print("DEPENDENCY RESOLUTION & RECIPE UPDATE SUMMARY")
print("=" * 80)

if updates_needed:
    print(f"\n[!] RECIPES TO UPDATE ({len(updates_needed)}):")
    print(f"  {'Recipe Name':<25} {'Current':<15} {'Resolved':<15} Recipe File")
    print("  " + "-" * 78)
    for r in updates_needed:
        print(f"  {r['dir_name']:<25} {r['recipe_ver']:<15} {r['resolved_ver']:<15} {r['bb_path']}")
else:
    print("\n[+] All recipes in recipes-python are up to date.")

if up_to_date:
    print(f"\n[=] RECIPES UP TO DATE ({len(up_to_date)}):")
    for r in up_to_date:
        print(f"  * {r['dir_name']:<25} {r['recipe_ver']}")

if downgrades:
    print(f"\n[?] RECIPES WITH LOWER RESOLVED VERSION ({len(downgrades)}):")
    for r in downgrades:
        print(f"  * {r['dir_name']:<25} Current: {r['recipe_ver']} -> Resolved: {r['resolved_ver']}")
        print(f"    Recipe: {r['bb_path']}")

if unresolved:
    print(f"\n[-] RECIPES NOT IN RESOLUTION REPORT ({len(unresolved)}):")
    for r in unresolved:
        print(f"  * {r['dir_name']:<25} (recipe version: {r['recipe_ver']})")

if transitive_pkgs:
    print(f"\n[*] TRANSITIVE / ADDITIONAL DEPENDENCIES RESOLVED ({len(transitive_pkgs)}):")
    for name, ver in sorted(transitive_pkgs, key=lambda x: x[0].lower()):
        print(f"  * {name}=={ver}")

print("\n" + "=" * 80)
print(f"Summary: {len(updates_needed)} recipe(s) to update, {len(up_to_date)} up to date, "
      f"{len(downgrades)} lower version, {len(unresolved)} unresolved.")
print("=" * 80)

if updates_needed:
    print("\nRecipes requiring update:")
    for r in updates_needed:
        print(f"  - {r['dir_name']}: {r['recipe_ver']} -> {r['resolved_ver']}")