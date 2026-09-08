SUMMARY = "PyTorch Image Models."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7594ac8072ac2e8f089e7382bff353ce"

inherit pypi python_pdm
SRC_URI[sha256sum] = "1af8d12bb7e15c5f96e98d60b7b8319cd7f31730778bf1af58e912a7ce171576"

RDEPENDS:${PN} += " \
    python3-huggingface-hub \
    python3-pytorch \
    python3-pyyaml \
    python3-safetensors \
    python3-torchvision \
"

