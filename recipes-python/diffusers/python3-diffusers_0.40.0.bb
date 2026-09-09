SUMMARY = "State-of-the-art diffusion in PyTorch and JAX."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d49094be2e38daba8ce0b1da5ef90d0b"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "49f112ce52ff6d332ab68afec01f79a53e6489b42cb69104444c98b4a2f64af8"

RDEPENDS:${PN} += " \
    python3-filelock \
    python3-httpx \
    python3-huggingface-hub\
    python3-importlib-metadata \
    python3-numpy \
    python3-pillow \
    python3-regex \
    python3-requests \
    python3-safetensors \
    "
