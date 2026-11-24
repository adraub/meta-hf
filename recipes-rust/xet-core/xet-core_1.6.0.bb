SUMMARY = "Hugging Face Xet storage client and Git LFS extension"
HOMEPAGE = "https://github.com/huggingface/xet-core"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = " \
    git://github.com/huggingface/xet-core.git;protocol=https;branch=main;tag=v${PV} \
"

SRCREV = "de71453d952bd8b806edaa997c72313051a49050"

inherit cargo cargo-update-recipe-crates pkgconfig

require ${BPN}-crates.inc

DEPENDS += "openssl"

# Build only the git-xet CLI binary from the workspace
CARGO_BUILD_FLAGS += "--package git_xet --bin git-xet"

# Ensure Cargo builds against the workspace Cargo.lock
CARGO_LOCK_PATH = "${S}/Cargo.lock"

# Remap vendored crate paths (e.g. C/C++ sources in cc/cmake crates) from CARGO_HOME
DEBUG_PREFIX_MAP += "-ffile-prefix-map=${CARGO_HOME}=${TARGET_DBGSRC_DIR}"