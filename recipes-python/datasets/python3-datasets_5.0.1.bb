SUMMARY = "HuggingFace community-driven open-source library of datasets."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "ce22bb851efd7494f08aad33b940803784434f6e77763d00679a0dc45fcf686a"

RDEPENDS:${PN} += " \
    python3-dill \
    python3-filelock \
    python3-fsspec \
    python3-httpx \
    python3-huggingface-hub \
    python3-multiprocess \
    python3-numpy \
    python3-packaging \
    python3-pandas \
    python3-pyarrow \
    python3-pyyaml \
    python3-requests \
    python3-tqdm \
    python3-xxhash \
"