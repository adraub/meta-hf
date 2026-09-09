SUMMARY = "Transformers: the model-definition framework for state-of-the-art machine learning models in text, vision, audio, and multimodal models, for both inference and training."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d15f16de23b5d7f8e28dd073d2ed8b28"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "a153be279169b55b92d8000bf4af294aed684503d091cca7804da2dd8a9de000"

RDEPENDS:${PN} += " \
    python3-huggingface-hub \
    python3-numpy \
    python3-packaging \
    python3-pyyaml \
    python3-regex \ 
    python3-safetensors \
    python3-tokenizers \
    python3-tqdm \
    python3-typer \
    "

PACKAGECONFIG[vision] = ",,,python3-pillow python3-torchvision"
PACKAGECONFIG[video] = ",,,python3-av"
PACKAGECONFIG[timm] = ",,,python3-timm"
PACKAGECONFIG[sentencepiece] = ",,,python3-sentencepiece python3-protobuf"
PACKAGECONFIG[tiktoken] = ",,,python3-tiktoken python3-blobfile"
PACKAGECONFIG[chat_template] = ",,,python3-jinja2"
