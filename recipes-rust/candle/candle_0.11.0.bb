SUMMARY = "Minimalist ML framework for Rust"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d \
                    file://candle-core/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/huggingface/candle.git;protocol=https;nobranch=1 \
           file://0001-Add-lockfile-for-recipecrates.patch"

SRCREV = "31f35b147389700ed2a178ee66a91c3cc25cc80d"

inherit cargo cargo-update-recipe-crates pkgconfig

DEPENDS += "openssl openssl-native"

export OPENSSL_NO_VENDOR = "1"

# Map target triple for aarch64
export AARCH64_UNKNOWN_LINUX_GNU_OPENSSL_DIR = "${RECIPE_SYSROOT}/usr"
export AARCH64_UNKNOWN_LINUX_GNU_OPENSSL_INCLUDE_DIR = "${RECIPE_SYSROOT}/usr/include"
export AARCH64_UNKNOWN_LINUX_GNU_OPENSSL_LIB_DIR = "${RECIPE_SYSROOT}/usr/lib"

# Point host OpenSSL to native sysroot for build scripts
export X86_64_UNKNOWN_LINUX_GNU_OPENSSL_DIR = "${RECIPE_SYSROOT_NATIVE}/usr"
export X86_64_UNKNOWN_LINUX_GNU_OPENSSL_INCLUDE_DIR = "${RECIPE_SYSROOT_NATIVE}/usr/include"
export X86_64_UNKNOWN_LINUX_GNU_OPENSSL_LIB_DIR = "${RECIPE_SYSROOT_NATIVE}/usr/lib"

# CARGO_BUILD_FLAGS += "--workspace --examples"

require candle-crates.inc
