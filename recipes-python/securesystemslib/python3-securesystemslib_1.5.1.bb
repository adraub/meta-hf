SUMMARY = "A library that provides cryptographic and general-purpose routines for Secure Systems Lab projects at NYU"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e9703d169911cdb29305f88411c78717"

inherit pypi python_hatchling
SRC_URI += "file://0001-Relax-hatchling-version.patch"
SRC_URI[sha256sum] = "4b8d00abd93707ead10b69eb2b8582376a1364de3b0a71077de534c2ef4985e0"

#Solves : QA Issue: /usr/lib/python3.14/site-packages/securesystemslib/_vendor/test-ed25519-upstream.sh contained in package python3-securesystemslib requires /bin/bash, but no providers found in RDEPENDS:python3-securesystemslib? [file-rdeps]
do_install:append() {
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/securesystemslib/_vendor/test-ed25519-upstream.sh
}


