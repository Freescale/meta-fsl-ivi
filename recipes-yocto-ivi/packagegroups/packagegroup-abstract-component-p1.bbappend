FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 0}"

RDEPENDS_${PN} = "\
	bluez5 \
	bluez5-obex \
	eglibc \
	ofono \
	${@base_contains("DISTRO_FEATURES", "wayland", "wayland-ivi-extension weston", "", d)} \
	${@base_contains("DISTRO_FEATURES", "x11", "${XSERVER} layer-management node-state-manager", "", d)} \
	"
