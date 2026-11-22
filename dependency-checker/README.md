Run check_rdepends_pypi on all bb of a folder:
```bash
find recipes-python -type f -name "*.bb" -exec python3 ci/check_rdepends_pypi.py {} \;
```

Resolve newest package version with yocto recipes
```bash
./get-latest-eligible-versions.sh
```