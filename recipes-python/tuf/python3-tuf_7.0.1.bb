SUMMARY = "A secure updater framework for Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8cc789b082b3d97e1ccc5261f8594d3f"

inherit pypi python_hatchling
SRC_URI += "file://0001-Relax-hatchling-requirements.patch"
SRC_URI[sha256sum] = "5ada1db78da3518fa851e28588f4b2249b8662a28ae528f54afa2a1de541fb31"

RDEPENDS:${PN} += "\
    python3-urllib3 \
    python3-securesystemslib \
    "



