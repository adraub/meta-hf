SUMMARY = "HuggingFace community-driven open-source library of evaluation."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3
SRC_URI[sha256sum] = "e07036ca12b3c24331f83ab787f21cc2dbf3631813a1631e63e40897c69a3f21"

RDEPENDS:${PN} += " \
    python3-datasets \
    python3-dill \
    python3-fsspec \
    python3-huggingface-hub \
    python3-multiprocess \
    python3-numpy \
    python3-packaging \
    python3-pandas \
    python3-requests \
    python3-tqdm \
    python3-xxhash \
"