SUMMARY = "Download compute kernels."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "b4a3fe0980dd1a3089920242e41b1551acabfea50d03bffbe63a5ce34449e6ae"
SRC_URI += "file://LICENSE"

RDEPENDS:${PN} += "\
    python3-huggingface-hub \
    python3-kernels-data \
    python3-packaging \
    python3-pyyaml \
    python3-sigstore \
    python3-tomlkit \
    "
