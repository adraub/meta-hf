SUMMARY = "A library that provides cryptographic and general-purpose routines for Secure Systems Lab projects at NYU"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e9703d169911cdb29305f88411c78717"

inherit pypi python_hatchling
SRC_URI += "file://0001-Relax-hatchling-version.patch"
SRC_URI[sha256sum] = "b16e07c32bb41174c2ee2fb18f478ebe0d52e0e7c1fb085ce89c990452a35977"

#Solves : QA Issue: /usr/lib/python3.14/site-packages/securesystemslib/_vendor/test-ed25519-upstream.sh contained in package python3-securesystemslib requires /bin/bash, but no providers found in RDEPENDS:python3-securesystemslib? [file-rdeps]
do_install:append() {
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/securesystemslib/_vendor/test-ed25519-upstream.sh
}


