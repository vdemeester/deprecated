# Gentoo specific Makefile
#

install: install-portage install-makeconf

install-portage:
	@echo ">>>> Gentoo Portage linking"
	sudo rm -fR /etc/portage/package.keywords /etc/portage/package.use /etc/portage/package.unmask
	sudo ln -s `pwd`/portage/package.use /etc/portage/package.use
	sudo ln -s `pwd`/portage/package.keywords /etc/portage/package.keywords
	sudo ln -s `pwd`/portage/package.unmask /etc/portage/package.unmask

install-makeconf:
	sudo rm -fR /etc/make.conf
	sudo ln -s `pwd`/make.conf /etc/make.conf
	
