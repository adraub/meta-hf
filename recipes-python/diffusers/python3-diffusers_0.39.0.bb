SUMMARY = "State-of-the-art diffusion in PyTorch and JAX."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d49094be2e38daba8ce0b1da5ef90d0b"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "14bb1d98c85a0e463d734c99aaa73b480a7bc9bad22af30fbf730ef8f09c1d67"

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
