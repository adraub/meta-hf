SUMMARY = "Transformers: the model-definition framework for state-of-the-art machine learning models in text, vision, audio, and multimodal models, for both inference and training."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d15f16de23b5d7f8e28dd073d2ed8b28"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "17b0eac726ddc55e84ac58946063e0c6d37fd000c456b581f050ea0f4e822869"

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
