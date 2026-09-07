# Script to be run inside the ci folder within kas venv

RECIPES_DIR="../recipes-python"

# Exclude recipes obtained by meta-hf
META_HF_RECIPES=$( {
    find "../recipes-"* -name "*.bb" -exec basename {} \; 2>/dev/null | sed -E 's/_[^_]+\.bb$//; s/\.bb$//; s/^python3-//';
    [ -d "${RECIPES_DIR}" ] && ls -1 "${RECIPES_DIR}" 2>/dev/null;
} | sort -u | tr '\n' ' ' )

# Packages to exclude/relax from yocto-python3-versions.txt constraints:
# - recipes obtained by meta-hf
# - mpmath, setuptools
EXCLUDE_PACKAGES="mpmath setuptools setuptools-declarative-requirements ${META_HF_RECIPES}"

kas shell ../kas/build-qemux86-64-rolling.yaml --command "bitbake -s" | awk -v exclude="${EXCLUDE_PACKAGES}" '
BEGIN {
    split(exclude, arr, " ");
    for (i in arr) {
        if (arr[i] != "") skip[arr[i]] = 1;
    }
}
/^python3/ && !/-native/ && !/-cross/ {
    name = $1;
    ver = $2;
    sub(/^python3-/, "", name);
    sub(/^.*:/, "", ver);
    sub(/-r[0-9]+.*$/, "", ver);
    sub(/\+git.*$/, "", ver);
    if (!(name in skip)) {
        print name "==" ver;
    }
}' > yocto-python3-versions.txt

# Replace recipe named pytorch by torch to match PyPi package naming
sed -i 's/\bpytorch==/torch==/g' yocto-python3-versions.txt 

ls -1 ../recipes-python/ > requirements.txt

mkdir -p venv-res
cd venv-res
python3 -m venv .
source bin/activate
cd ..

RESOLUTION_REPORT="resolved-dependencies.json"
RESOLVED_VERSIONS="resolved-requirements.txt"

echo "Resolving dependencies with pip (dry-run)..."
if ! pip install -r requirements.txt -c yocto-python3-versions.txt --dry-run --report "${RESOLUTION_REPORT}"; then
    echo "Error: pip dry-run dependency resolution failed." >&2
    exit 1
fi

if [ ! -f "${RESOLUTION_REPORT}" ]; then
    echo "Error: Dependency resolution report '${RESOLUTION_REPORT}' was not generated." >&2
    exit 1
fi

python3 dependency_resolution.py "${RECIPES_DIR}" "${RESOLUTION_REPORT}" "${RESOLVED_VERSIONS}"

