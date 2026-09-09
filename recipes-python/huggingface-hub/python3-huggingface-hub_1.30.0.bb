SUMMARY = "The official CLI and Python client for the Hugging Face Hub."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PYPI_PACKAGE = "huggingface_hub"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "e6a6120bc8c8e2723d03648434ee247088cceb55ba7067e7d34d692cad5fdb57"

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
