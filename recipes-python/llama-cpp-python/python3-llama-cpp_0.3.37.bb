SUMMARY = "Python bindings for llama.cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7b314940bc52f236ef5c740707a5a216"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "llama_cpp_python"
SRC_URI[sha256sum] = "bf4108fc76e933ea4969b61b1e281a2d232dd3e43361d1fdca5e2e029d62f729"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)}"
PACKAGECONFIG[vulkan] = "-DGGML_VULKAN=ON -DVulkan_INCLUDE_DIR=${STAGING_INCDIR} -DVulkan_LIBRARY=${STAGING_LIBDIR}/libvulkan.so -DGGML_SHADER_NATIVE_PATH=${STAGING_DIR_NATIVE},-DGGML_VULKAN=OFF,vulkan-headers vulkan-loader spirv-headers shaderc-native,vulkan-loader"

# Disable ccache,enable openblas
export CMAKE_ARGS = "-DGGML_CCACHE=OFF \
    -DGGML_NATIVE=OFF \
    -DGGML_BLAS=ON \
    -DGGML_BLAS_VENDOR=OpenBLAS \
    -DBLAS_INCLUDE_DIRS=${STAGING_INCDIR}/openblas \
    ${PACKAGECONFIG_CONFARGS} \
    -DCMAKE_SYSTEM_NAME=Linux \
    -DCMAKE_SYSTEM_PROCESSOR=${TARGET_ARCH} \
    -DOPENSSL_ROOT_DIR=${STAGING_DIR_TARGET}${prefix} \
    -DOPENSSL_INCLUDE_DIR=${STAGING_INCDIR} \
    -DOPENSSL_LIBRARIES=${STAGING_LIBDIR} \
    "

# Point the Vulkan discovery to the target sysroot (equivalent to setup_env.sh)
export VULKAN_SDK = "${@bb.utils.contains('PACKAGECONFIG', 'vulkan', '${STAGING_DIR_TARGET}/usr', '', d)}"

# Solves: Multiple shlib providers
do_install:append() {
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/lib
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/include
}

# Solves: Already stripped error
INSANE_SKIP:${PN} += "already-stripped"

DEPENDS += "\
    python3-scikit-build-core-native \
    python3-setuptools-native \
    cmake-native \
    ninja-native \
    openblas \
    openssl \
    "

RDEPENDS:${PN} += "\
    python3-diskcache \
    python3-jinja2 \
    python3-numpy \
    python3-typing-extensions \
    openblas \
    openssl \
    "
