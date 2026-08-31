SUMMARY = "PyTorch Image Models."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7594ac8072ac2e8f089e7382bff353ce"

inherit pypi python_pdm
SRC_URI[sha256sum] = "7468d32a410c359181c1ef961f49c7e213286e0c342bfb898b99534a4221fc54"

RDEPENDS:${PN} += " \
    python3-huggingface-hub \
    python3-pytorch \
    python3-pyyaml \
    python3-safetensors \
    python3-torchvision \
"

