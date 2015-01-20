FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 0}"

PACKAGECONFIG_remove_mx6 = " gallium gallium-egl gallium-llvm"

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
