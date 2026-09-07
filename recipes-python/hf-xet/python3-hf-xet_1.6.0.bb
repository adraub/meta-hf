SUMMARY = "Fast transfer of large files with the Hugging Face Hub."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

S = "${UNPACKDIR}/${BP}/hf_xet"

PYPI_PACKAGE = "hf_xet"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "73044bd31bae33c984af832d19c752a0dffb67518fee9ddbd91d616e1101cf47"

include ${BPN}-crates.inc
