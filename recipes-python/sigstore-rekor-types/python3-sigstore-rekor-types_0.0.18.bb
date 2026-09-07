SUMMARY = "Python models for Rekor's API types"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PYPI_PACKAGE = "sigstore_rekor_types"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "19aef25433218ebf9975a1e8b523cc84aaf3cd395ad39a30523b083ea7917ec5"

RDEPENDS:${PN} += "\
    python3-pydantic \
    "


