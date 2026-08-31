SUMMARY = "State-of-the-art diffusion in PyTorch and JAX."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d49094be2e38daba8ce0b1da5ef90d0b"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "2346c21f77f835f273b7aacbaada1c34a596a3a2cc6ddc99d149efcd0ec298fa"

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
