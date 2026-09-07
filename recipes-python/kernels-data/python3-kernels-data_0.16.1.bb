SUMMARY = "Kernels data structures (Python bindings)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PYPI_PACKAGE = "kernels_data"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "7dc804a5f5f2b8903a2f07d96f6c8c428a539d5da26c7f91e9687a38e19b3f12"
SRC_URI += "file://LICENSE"

# Include the generated Rust crate dependencies
include ${BPN}-crates.inc