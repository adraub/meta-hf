SUMMARY = "Simple, safe way to store and distribute tensors."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

# Point source path to the python bindings directory inside the repo
S = "${UNPACKDIR}/${BP}/bindings/python"

inherit pypi cargo python_maturin cargo-update-recipe-crates
SRC_URI[sha256sum] = "fabaf3e0f18a6618d9b36560682562157f77c2b71fcffc7b432be2baed9d753d"

# Include the generated Rust crate dependencies
include ${BPN}-crates.inc
