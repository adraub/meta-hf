SUMMARY = "Barebones library for agents that think in Python code"
DESCRIPTION = "smolagents is a lightweight library for building agents that write Python code to call tools or orchestrate other agents."
HOMEPAGE = "https://github.com/huggingface/smolagents"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "4ec92313265f9cfbcabfc88e192b4bc4505f8475dc5f33dc872062fc567037bd"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-huggingface-hub \
    python3-jinja2 \
    python3-pillow \
    python3-python-dotenv \
    python3-requests \
    python3-rich \
"