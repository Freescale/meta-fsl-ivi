DESCRIPTION = "LayerManager"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "environment/base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=249d3578d6bba1bb946148d367a28080"

PR = "r0"
DEPENDS = "virtual/egl virtual/libgles2 dbus"
RDEPENDS_${PN} = "linux-module-virtfb"

SRCREV = "ec06dda5d1de3723a8694080c19554c73ac346f2"
SRC_URI = "\
	git://git.projects.genivi.org/layer_management.git;protocol=git \
	file://point-to-internal-CMakeVersions.patch \
	file://0001-config.h.cmake-add-mx-vfb-config-options.patch \
	file://0001-BugFix-Correct-return-type-for-recently-modified-fun.patch \
	file://0001-LayerManagerPlugin-First-version-of-MX6-GAL-virtual-.patch \
	file://0002-LayerManagerExamples-Add-EGLVFB-versions-of-example-.patch \
	file://0001-FindMXGAL-fix-search-path-for-libGAL.patch \
	file://0001-ilmClient-add-pthread-link-dependency.patch \
	file://0001-MXGAL-fix-undeclared-definition-of-close.patch \
	file://0001-VFBMXGALRenderer-fix-config.h-no-such-of-file.patch \
	file://0001-OpenGLES2App-include-unistd.h-as-usleep-is-used.patch \
	file://0001-Improve-plugin-search-over-specific-filesystem-type.patch \
	file://0001-MXGALGraphicSystem-remove-noise-verbose-log-messages.patch \
	file://layermanager.service \
    "

OECMAKE_CXX_FLAGS_append_mx6 = " -DLINUX"
EXTRA_OECMAKE = "${@base_contains('DISTRO_FEATURES', 'x11', \
	' -DWITH_X11_GLES=ON -DWITH_XTHREADS=OFF -DWITH_VFB_MX=OFF -DWITH_MXGAL_LIB=OFF', \
	' -DWITH_VFB_MX=ON -DWITH_MXGAL_LIB=ON -DWITH_X11_GLES=OFF -DWITH_XTHREADS=OFF', d)}"
EXTRA_OECMAKE += " -DWITH_EGL_EXAMPLE=ON -DWITH_TESTS=ON -DWITH_CLIENTEXAMPLES=ON"

S = "${WORKDIR}/git"

inherit gettext cmake systemd

SYSTEMD_SERVICE = "layermanager.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} += " \
	${libdir}/lib* \
	${libdir}/layermanager/lib* \
	${libdir}/layermanager/communicator/lib* \
	${libdir}/layermanager/ipcmodules/lib* \
	${libdir}/layermanager/renderer/lib* \
	${libdir}/layermanager/renderer/renderer* \
	${systemd_unitdir}/system/layermanager.service \
	"

FILES_${PN}-dev = " \
	${includedir}/* \
	"
FILES_${PN}-staticdev += " \
	${libdir}/layermanager/static/lib* \
	"
FILES_${PN}-dbg += " \
	${libdir}/layermanager/.debug/ \
	${libdir}/layermanager/communicator/.debug/ \
	${libdir}/layermanager/ipcmodules/.debug/ \
	${libdir}/layermanager/renderer/.debug/ \
	"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/layermanager.service ${D}${systemd_unitdir}/system
}
