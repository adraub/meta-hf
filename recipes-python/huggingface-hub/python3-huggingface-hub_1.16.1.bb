SUMMARY = "The official CLI and Python client for the Hugging Face Hub."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PYPI_PACKAGE = "huggingface_hub"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "7f1dc4c5ec21aed69be630ad0c3378616be16f3de1a47b141c0e812965d9c832"

RDEPENDS:${PN} += "\
    python3-filelock \
    python3-fsspec \
    python3-hf-xet \
    python3-httpx \
    python3-packaging \
    python3-pyyaml \
    python3-tqdm \
    python3-typer \
    python3-typing-extensions \
    "
