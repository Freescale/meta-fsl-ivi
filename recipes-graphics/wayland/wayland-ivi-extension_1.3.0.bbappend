FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Set EGL/eglvivante.h platform
OECMAKE_CXX_FLAGS_append_mx6 = " -DLINUX -DEGL_API_FB -DEGL_API_WL"

PACKAGE_ARCH_mx6 = "${MACHINE_SOCARCH}"
