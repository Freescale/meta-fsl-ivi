# Copyright (C) 2013 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Virtual frame buffer driver for mx6"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
	file://virtual_fb.c;endline=12;md5=5d5f252b6265bc61bba3f40b34b1edbf"
PR = "r1"

inherit module systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "virtfb.service"
SYSTEMD_AUTO_ENABLE = "disable"

SRCREV = "aea99eef8f5486af2fedbc04c42f127615443371"
SRC_URI = "git://github.com/Freescale/linux-module-virtfb.git;protocol=git \
	file://virtfb.service \
	file://virtfb.sh \
	"

S = "${WORKDIR}/git"

module_do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${KERNEL_AR}" \
		-C ${STAGING_KERNEL_DIR} SUBDIRS=${S}
}

module_do_install() {
	install -d ${D}${sysconfdir}/virtfb
	install -d ${D}${systemd_unitdir}/system

	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
		-C ${STAGING_KERNEL_DIR} SUBDIRS=${S} \
		INSTALL_MOD_PATH="${D}" modules_install

	install -m 0755 ${WORKDIR}/virtfb.sh ${D}${sysconfdir}/virtfb
	install -m 0644 ${WORKDIR}/virtfb.service ${D}${systemd_unitdir}/system
}

RDEPENDS_${PN} = "kmod"
FILES_${PN} += "${sysconfdir}/virtfb/virtfb.sh \
	${systemd_unitdir}/system/virtfb.service"

COMPATIBLE_MACHINE = "(mx6)"
