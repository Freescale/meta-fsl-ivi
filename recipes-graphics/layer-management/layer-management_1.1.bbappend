FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

# Set EGL/eglvivante.h platform
OECMAKE_CXX_FLAGS_append_mx6 = " -DLINUX"

EXTRA_OECMAKE_mx6 += " -DWITH_FORCE_COPY=ON"
PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
