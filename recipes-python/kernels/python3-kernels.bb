SUMMARY = "Download compute kernels."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

include kernels-version.inc

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "4cf3be5e133ff5c7f5114410bffd3eee1eb98c6abd71d6ccbeb8307170364d50"
SRC_URI += "file://LICENSE"

RDEPENDS:${PN} += "\
    python3-huggingface-hub \
    python3-kernels-data \
    python3-packaging \
    python3-pyyaml \
    python3-sigstore \
    python3-tomlkit \
    "
