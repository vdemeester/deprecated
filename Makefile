# Makefile for configs :)
UNAME		:= $(shell uname)
HOST		:= $(shell hostname -s)

uname_targets = $(addprefix install-uname-,$(UNAME))
host_targets = $(addprefix install-host-,$(HOST))

install: echo install-common ${uname_targets} ${host_targets}

echo:
	@echo Installing configuration files
	@echo ">> Uname : ${UNAME}"
	@echo ">> Host  : ${HOST}"

install-uname-Linux: install-xorg install-xmonad install-bin

install-uname-Darwin: 

install-host-kyushu: install-gentoo-stuff

install-host-gohei:

install-host-vinssento.home:

# Commons rules
install-common: install-vim install-zsh

install-vim:
	@echo ">>> vim linking"
	@rm -fR ~/.vim ~/.vimrc ~/.vimrc.bepo
	@ln -s `pwd`/vim ~/.vim
	@ln -s ~/.vim/vimrc ~/.vimrc
	@ln -s ~/.vim/vimrc.bepo ~/.vimrc.bepo

install-zsh: install-inputrc
	@echo ">>> zsh linking"
	@rm -fR ~/.zlogin ~/.zlogout ~/.zsh ~/.zshrc ~/.zshenv
	@ln -s `pwd`/zsh ~/.zsh
	@ln -s ~/.zsh/env ~/.zshenv
	@ln -s ~/.zsh/login ~/.zlogin
	@ln -s ~/.zsh/logout ~/.zlogout
	@ln -s ~/.zsh/rc ~/.zshrc

install-inputrc:
	@rm -fR ~/.inputrc
	@ln -s `pwd`/inputrc ~/.inputrc

install-xorg: install-fonts
	@echo ">>> X.org stuff linking"
	@rm -fR ~/.Xdefaults ~/.xinitrc ~/.xinitrc.d ~/.colours
	@ln -s `pwd`/Xdefaults ~/.Xdefaults
	@ln -s `pwd`/xinitrc ~/.xinitrc
	@ln -s `pwd`/xinitrc.d ~/.xinitrc.d
	@ln -s `pwd`/colours ~/.colours

install-fonts:
	@echo ">>> fonts config linking"
	@rm -fR ~/.fonts.conf
	@ln -s `pwd`/fonts.conf ~/.fonts.conf

install-xmonad:
	@echo ">>> Xmonad linking… nerd stuff"
	@rm -fR ~/.xmonad ~/.dzen_conkyrc
	@ln -s `pwd`/xmonad ~/.xmonad

install-bin:
	@echo ">>> binaries linking"
	@rm -fR ~/.bin
	@ln -s `pwd`/bin ~/.bin

# Gentoo
install-gentoo-stuff: install-gentoo-portage

install-gentoo-portage:
	@echo ">>> Gentoo Portage linking"
	@sudo rm -fR /etc/make.conf
	@sudo rm -fR /etc/portage/package.keywords /etc/portage/package.use /etc/portage/package.unmask
	@sudo ln -s `pwd`/gentoo/make.conf /etc/make.conf
	@sudo ln -s `pwd`/gentoo/portage/package.use /etc/portage/package.use
	@sudo ln -s `pwd`/gentoo/portage/package.keywords /etc/portage/package.keywords
	@sudo ln -s `pwd`/gentoo/portage/package.unmask /etc/portage/package.unmask

