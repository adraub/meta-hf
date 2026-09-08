Run check_rdepends_pypi on all bb of a folder:
```bash
find recipes-python -type f -name "*.bb" -exec python ci/check_rdepends_pypi.py {} \;
```