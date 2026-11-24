SUMMARY = "PyTorch Image Models."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7594ac8072ac2e8f089e7382bff353ce"

inherit pypi python_pdm
SRC_URI[sha256sum] = "baa0b333339c46d3d6d4965d99b69f5053afed71210e8f433d7ea5ee6a18f0ec"

RDEPENDS:${PN} += " \
    python3-huggingface-hub \
    python3-pytorch \
    python3-pyyaml \
    python3-safetensors \
    python3-torchvision \
"

