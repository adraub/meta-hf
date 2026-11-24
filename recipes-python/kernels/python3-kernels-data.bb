SUMMARY = "Kernels data structures (Python bindings)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

include kernels-version.inc
PYPI_PACKAGE = "kernels_data"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "c45c88b8c49b8d8130615b5ea78b51621b5c5c94ea47c902d6002750abe6108a"
SRC_URI += "file://LICENSE"

# Include the generated Rust crate dependencies
include ${BPN}-crates.inc