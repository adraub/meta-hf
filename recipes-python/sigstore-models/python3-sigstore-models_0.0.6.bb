SUMMARY = "Pydantic based models for Sigstore's protobuf specifications"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PYPI_PACKAGE = "sigstore_models"

inherit pypi python_setuptools_build_meta
SRC_URI += "file://0001-Relax-uv-build-requirements.patch"
SRC_URI[sha256sum] = "c766c09470c2a7e8a4a333c893f07e2001c56a3ff1757b1a246119f53169a849"

DEPENDS += "python3-uv-build-native"

RDEPENDS:${PN} += "\
    python3-pydantic \
    python3-typing-extensions \
    "


