SUMMARY = "Tensor library for machine learning"
DESCRIPTION = "GGML is a C/C++ tensor library for machine learning supporting CPU, BLAS, and GPU backends."
HOMEPAGE = "https://github.com/ggml-org/ggml"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

SRC_URI = "git://github.com/ggml-org/ggml.git;protocol=https;branch=master;tag=v${PV}"
SRCREV = "417a024b4fb6bc3116d41af7c067166a039bdf12"

inherit cmake pkgconfig

# Enable OpenBLAS and OpenMP by default; enable Vulkan automatically if "vulkan" is in DISTRO_FEATURES
PACKAGECONFIG ??= "openblas openmp ${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)}"

PACKAGECONFIG[openblas] = "-DGGML_BLAS=ON -DGGML_BLAS_VENDOR=OpenBLAS,-DGGML_BLAS=OFF,openblas"
PACKAGECONFIG[vulkan] = "-DGGML_VULKAN=ON -DVulkan_GLSLC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/glslc,-DGGML_VULKAN=OFF,vulkan-loader vulkan-headers spirv-headers shaderc-native"
PACKAGECONFIG[openmp] = "-DGGML_OPENMP=ON,-DGGML_OPENMP=OFF"
PACKAGECONFIG[backend-dl] = "-DGGML_BACKEND_DL=ON,-DGGML_BACKEND_DL=OFF"

EXTRA_OECMAKE += " \
    -DBUILD_SHARED_LIBS=ON \
    -DGGML_NATIVE=OFF \
    -DGGML_CCACHE=OFF \
    -DGGML_BUILD_TESTS=OFF \
    -DGGML_BUILD_EXAMPLES=OFF \
"