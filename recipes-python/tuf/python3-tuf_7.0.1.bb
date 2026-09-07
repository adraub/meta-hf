SUMMARY = "A secure updater framework for Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit pypi python_hatchling
SRC_URI += "file://0001-Relax-hatchling-requirements.patch"
SRC_URI[sha256sum] = "5ada1db78da3518fa851e28588f4b2249b8662a28ae528f54afa2a1de541fb31"

RDEPENDS:${PN} += "\
    python3-urllib3 \
    python3-securesystemslib \
    "



