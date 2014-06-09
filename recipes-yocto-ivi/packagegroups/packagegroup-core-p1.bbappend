FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 0}"

# Remove x11 dependency if not in DISTRO_FEATURES
RRECOMMENDS_${PN} =  "${@base_contains("DISTRO_FEATURES", "x11", "packagegroup-xserver-ivi", "", d)}"
