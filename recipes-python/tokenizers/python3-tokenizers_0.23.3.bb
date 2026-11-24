SUMMARY = "Fast State-of-the-Art Tokenizers optimized for Research and Production."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://tokenizers/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${UNPACKDIR}/${BP}/bindings/python"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "cded33237c77caeef62944d32aa9a7ef42bdce2b3497e18d137e072a8c4be438"

RDEPENDS:${PN} += "python3-huggingface-hub "

require ${BPN}-crates.inc
