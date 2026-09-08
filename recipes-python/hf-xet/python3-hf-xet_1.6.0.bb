SUMMARY = "Fast transfer of large files with the Hugging Face Hub."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${UNPACKDIR}/${BP}/hf_xet"

PYPI_PACKAGE = "hf_xet"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "2e58454a340b3556dfa4972d5451aff4fba8dd42a236600ba1a1d2b1514f0fef"

include ${BPN}-crates.inc
