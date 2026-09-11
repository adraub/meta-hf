# meta-hf
OpenEmbedded/Yocto layer providing recipes for Hugging Face open-source libraries

## DISCLAIMER
This project is an unofficial Yocto layer. It is not affiliated with, endorsed by, or sponsored by Hugging Face, Inc.

## Installation
This project depends heavily on [meta-python-ai](https://github.com/zboszor/meta-python-ai) layer.

[KAS](https://kas.readthedocs.io/en/latest/intro.html) yaml configuration are provided to ease setup. Please note the image definition are for debug only, as they define passwordless root login to ease debugging via qemu.

## Compatibility
As transformers and diffusers contains a lot of Rust dependency, those recipes are only available from yocto wrynose. Previous yocto release did not provide sufficient Rust compiler.

## Contributing

You are encouraged to follow Github Pull request workflow
to share changes and following commit message guidelines are recommended: [OE patch guidelines](https://www.openembedded.org/wiki/Commit_Patch_Message_Guidelines).

Maintainer : <adraub@gmail.com>
