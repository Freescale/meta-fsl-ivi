FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 0}"

DEPENDS_mx6 = "virtual/egl virtual/libgles2"

SRC_URI_append_mx6 += "file://FindEGL.cmake-add-prefix-for-FSL-EGL-x11-prebuilt-li.patch"

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
