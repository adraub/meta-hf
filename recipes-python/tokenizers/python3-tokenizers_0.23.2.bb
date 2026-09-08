SUMMARY = "Fast State-of-the-Art Tokenizers optimized for Research and Production."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://tokenizers/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${UNPACKDIR}/${BP}/bindings/python"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "7f0f085686b9de0d0079e6f874ae053600db64c5d13049e0bbc0119926d25aac"

RDEPENDS:${PN} += "python3-huggingface-hub "

require ${BPN}-crates.inc
