SUMMARY = "High-performance inference of OpenAI's Whisper ASR model in C/C++"
HOMEPAGE = "https://github.com/ggml-org/whisper.cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

SRCREV = "96ec4d9823e9d0211944172da35f9f29134af990"
SRC_URI = "git://github.com/ggml-org/whisper.cpp.git;protocol=https;branch=master;tag=v${PV}"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DWHISPER_BUILD_IS_DEV=OFF \
    -DBUILD_SHARED_LIBS=ON \
    -DGGML_NATIVE=OFF \
    -DGGML_CCACHE=OFF \
    -DWHISPER_BUILD_TESTS=OFF \
"

PACKAGECONFIG ??= "examples server ${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)}"

PACKAGECONFIG[examples]    = "-DWHISPER_BUILD_EXAMPLES=ON,-DWHISPER_BUILD_EXAMPLES=OFF"
PACKAGECONFIG[server]      = "-DWHISPER_BUILD_SERVER=ON,-DWHISPER_BUILD_SERVER=OFF"
PACKAGECONFIG[openmp]      = "-DGGML_OPENMP=ON,-DGGML_OPENMP=OFF,libgomp"
PACKAGECONFIG[curl]        = "-DWHISPER_CURL=ON,-DWHISPER_CURL=OFF,curl"
PACKAGECONFIG[sdl2]        = "-DWHISPER_SDL2=ON,-DWHISPER_SDL2=OFF,libsdl2"
PACKAGECONFIG[ffmpeg]      = "-DWHISPER_COMMON_FFMPEG=ON,-DWHISPER_COMMON_FFMPEG=OFF,ffmpeg"
PACKAGECONFIG[openblas]    = "-DGGML_BLAS=ON -DGGML_BLAS_VENDOR=OpenBLAS,-DGGML_BLAS=OFF,openblas"
PACKAGECONFIG[vulkan]      = "-DGGML_VULKAN=ON,-DGGML_VULKAN=OFF,vulkan-loader vulkan-headers spirv-headers shaderc-native"
PACKAGECONFIG[system-ggml] = "-DWHISPER_USE_SYSTEM_GGML=ON,-DWHISPER_USE_SYSTEM_GGML=OFF,ggml"

PACKAGES =+ "${PN}-cli"
FILES:${PN}-cli = "${bindir}/*"