SUMMARY = "A tool for signing Python package distributions"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "020d3e07f622b2916bf453e66ff6ff0711e1fdc5ab69e8bd8902f71d9fcb316f"

RDEPENDS:${PN} += "\
    python3-cryptography \
    python3-id \
    python3-pyasn1 \
    python3-pydantic \
    python3-pyjwt \
    python3-pyopenssl \
    python3-requests \
    python3-rich \
    python3-rfc8785 \
    python3-rfc3161-client \
    python3-sigstore-models \
    python3-sigstore-rekor-types \
    python3-tuf \
    python3-platformdirs \
    "


