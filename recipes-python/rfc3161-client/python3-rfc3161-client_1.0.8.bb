SUMMARY = "An Opinionated Python RFC3161 Client"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

PYPI_PACKAGE = "rfc3161_client"

inherit pypi python_maturin cargo-update-recipe-crates pkgconfig
SRC_URI += "crate://crates.io/anyhow/1.0.102 \
           crate://crates.io/asn1/0.24.1 \
           crate://crates.io/asn1_derive/0.24.1 \
           crate://crates.io/bitflags/2.11.1 \
           crate://crates.io/block-buffer/0.12.0 \
           crate://crates.io/cc/1.2.60 \
           crate://crates.io/cfg-if/1.0.4 \
           crate://crates.io/chacha20/0.10.0 \
           crate://crates.io/const-oid/0.10.2 \
           crate://crates.io/cpufeatures/0.3.0 \
           crate://crates.io/crypto-common/0.2.1 \
           crate://crates.io/digest/0.11.2 \
           crate://crates.io/equivalent/1.0.2 \
           crate://crates.io/find-msvc-tools/0.1.9 \
           crate://crates.io/foldhash/0.1.5 \
           crate://crates.io/foreign-types/0.3.2 \
           crate://crates.io/foreign-types-shared/0.1.1 \
           crate://crates.io/getrandom/0.4.2 \
           crate://crates.io/hashbrown/0.15.5 \
           crate://crates.io/hashbrown/0.17.0 \
           crate://crates.io/heck/0.5.0 \
           crate://crates.io/hex/0.4.3 \
           crate://crates.io/hybrid-array/0.4.10 \
           crate://crates.io/id-arena/2.3.0 \
           crate://crates.io/indexmap/2.14.0 \
           crate://crates.io/itoa/1.0.18 \
           crate://crates.io/leb128fmt/0.1.0 \
           crate://crates.io/libc/0.2.185 \
           crate://crates.io/log/0.4.29 \
           crate://crates.io/memchr/2.8.0 \
           crate://crates.io/once_cell/1.21.4 \
           crate://crates.io/openssl/0.10.81 \
           crate://crates.io/openssl-macros/0.1.1 \
           crate://crates.io/openssl-src/300.6.0+3.6.2 \
           crate://crates.io/openssl-sys/0.9.117 \
           crate://crates.io/pkg-config/0.3.33 \
           crate://crates.io/portable-atomic/1.13.1 \
           crate://crates.io/prettyplease/0.2.37 \
           crate://crates.io/proc-macro2/1.0.106 \
           crate://crates.io/pyo3/0.29.0 \
           crate://crates.io/pyo3-build-config/0.29.0 \
           crate://crates.io/pyo3-ffi/0.29.0 \
           crate://crates.io/pyo3-macros/0.29.0 \
           crate://crates.io/pyo3-macros-backend/0.29.0 \
           crate://crates.io/quote/1.0.45 \
           crate://crates.io/r-efi/6.0.0 \
           crate://crates.io/rand/0.10.2 \
           crate://crates.io/rand_core/0.10.1 \
           crate://crates.io/self_cell/1.3.0 \
           crate://crates.io/semver/1.0.28 \
           crate://crates.io/serde/1.0.228 \
           crate://crates.io/serde_core/1.0.228 \
           crate://crates.io/serde_derive/1.0.228 \
           crate://crates.io/serde_json/1.0.149 \
           crate://crates.io/sha2/0.11.0 \
           crate://crates.io/shlex/1.3.0 \
           crate://crates.io/syn/2.0.117 \
           crate://crates.io/target-lexicon/0.13.5 \
           crate://crates.io/typenum/1.19.0 \
           crate://crates.io/unicode-ident/1.0.24 \
           crate://crates.io/unicode-xid/0.2.6 \
           crate://crates.io/vcpkg/0.2.15 \
           crate://crates.io/wasip2/1.0.2+wasi-0.2.9 \
           crate://crates.io/wasip3/0.4.0+wasi-0.3.0-rc-2026-01-06 \
           crate://crates.io/wasm-encoder/0.244.0 \
           crate://crates.io/wasm-metadata/0.244.0 \
           crate://crates.io/wasmparser/0.244.0 \
           crate://crates.io/wit-bindgen/0.51.0 \
           crate://crates.io/wit-bindgen-core/0.51.0 \
           crate://crates.io/wit-bindgen-rust/0.51.0 \
           crate://crates.io/wit-bindgen-rust-macro/0.51.0 \
           crate://crates.io/wit-component/0.244.0 \
           crate://crates.io/wit-parser/0.244.0 \
           crate://crates.io/zmij/1.0.21 \
           file://0001-Copy-cryptography-x509-source-from-python-cryptograp.patch \
           "
SRC_URI[sha256sum] = "4bda5a2bc6947c16b6f8df90ff0e99cb333d78ab1465517f637d313d75703651"

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

RDEPENDS:${PN} += "\
    python3-cryptography \
    "

include ${BPN}-crates.inc


