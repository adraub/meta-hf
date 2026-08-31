SUMMARY = "Simple, safe way to store and distribute tensors."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

# Point source path to the python bindings directory inside the repo
S = "${UNPACKDIR}/${BP}/bindings/python"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "07663963b67e8bd9f0b8ad15bb9163606cd27cc5a1b96235a50d8369803b96b0"

# Include the generated Rust crate dependencies
include ${BPN}-crates.inc
