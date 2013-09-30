FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 0}"

DEPENDS_mx6 = "virtual/egl virtual/libgles2"

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
