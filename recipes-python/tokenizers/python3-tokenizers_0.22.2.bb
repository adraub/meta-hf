SUMMARY = "Fast State-of-the-Art Tokenizers optimized for Research and Production."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://tokenizers/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${UNPACKDIR}/${BP}/bindings/python"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "473b83b915e547aa366d1eee11806deaf419e17be16310ac0a14077f1e28f917"

RDEPENDS:${PN} += "python3-huggingface-hub "

require ${BPN}-crates.inc
