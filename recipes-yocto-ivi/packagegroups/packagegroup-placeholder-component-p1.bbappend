FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 0}" 

# Conditional x11 dependency
RRECOMMENDS_${PN} = "${@base_contains('DISTRO_FEATURES', 'x11', \
	'packagegroup-xserver-ivi', '', d)}"
