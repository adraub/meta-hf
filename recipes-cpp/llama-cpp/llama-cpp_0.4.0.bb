SUMMARY = "llama.cpp."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

SRC_URI = "git://github.com/ggml-org/llama.cpp.git;protocol=https;nobranch=1"
inherit cmake

SRCREV = "5266f24da75dc449bd56cbed7addb9c8e4a6a73e"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)}"
PACKAGECONFIG[vulkan] = "-DGGML_VULKAN=ON -DVulkan_INCLUDE_DIR=${STAGING_INCDIR} -DVulkan_LIBRARY=${STAGING_LIBDIR}/libvulkan.so -DGGML_SHADER_NATIVE_PATH=${STAGING_DIR_NATIVE},-DGGML_VULKAN=OFF,vulkan-headers vulkan-loader spirv-headers shaderc-native,vulkan-loader"

# Disable ccache,enable openblas
EXTRA_OECMAKE += "-DGGML_CCACHE=OFF \
    -DGGML_NATIVE=OFF \
    -DGGML_BLAS=ON \
    -DGGML_BLAS_VENDOR=OpenBLAS \
    -DLLAMA_BUILD_TESTS= OFF \
    -DLLAMA_STATIC=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DBLAS_INCLUDE_DIRS=${STAGING_INCDIR}/openblas \
    ${PACKAGECONFIG_CONFARGS} \
    -DCMAKE_SYSTEM_NAME=Linux \
    -DCMAKE_SYSTEM_PROCESSOR=${TARGET_ARCH} \
    -DOPENSSL_ROOT_DIR=${STAGING_DIR_TARGET}${prefix} \
    -DOPENSSL_INCLUDE_DIR=${STAGING_INCDIR} \
    -DOPENSSL_LIBRARIES=${STAGING_LIBDIR} \
    "

# Prepend a dedicated package for the unversioned implementation libraries
PACKAGES =+ "${PN}-impl"
# Assign the implementation libraries to this package
FILES:${PN}-impl = "${libdir}/libllama-*-impl.so"
# Ensure the main package pulls in the implementation libraries at runtime
RDEPENDS:${PN} += "${PN}-impl"

#  QA Issue: File /usr/lib/cmake/ggml/ggml-config.cmake in package llama-cpp-dev contains reference to TMPDIR
INSANE_SKIP:${PN}-dev += "buildpaths"

# Point the Vulkan discovery to the target sysroot (equivalent to setup_env.sh)
export VULKAN_SDK = "${@bb.utils.contains('PACKAGECONFIG', 'vulkan', '${STAGING_DIR_TARGET}/usr', '', d)}"

DEPENDS += "\
    cmake-native \
    ninja-native \
    openblas \
    openssl \
    "

RDEPENDS:${PN} += "\
    openblas \
    openssl \
    "
