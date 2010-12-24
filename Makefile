# Makefile for configs :)
UNAME		 := $(shell uname)
HOST		 := $(shell hostname -s)
DISTRIB_FILE := $(shell ls -d /etc/*[-_]{version,release} 2>/dev/null)
DISTRIB_NAME := $(shell cat ${DISTRIB_FILE})
# Find a tool to read/understant the content of DISTRIB_FILE

uname_targets = $(addprefix install-uname-,$(UNAME))
host_targets = $(addprefix install-host-,$(HOST))

install: echo install-common ${uname_targets} ${host_targets}

echo:
	@echo Installing configuration files
	@echo ">> Uname    : ${UNAME}"
	@echo ">> Distrib. : ${DISTRIB_NAME}"
	@echo ">> Host     : ${HOST}"
	@echo ""

install-uname-Linux: install-xorg install-xmonad install-bin

install-uname-Darwin: 

install-host-kyushu:
	@cd gentoo; $(MAKE) install

install-host-gohei:

install-host-vinssento.home:

# Commons rules
install-common: install-vim install-zsh

install-vim:
	@echo ">>> vim linking"
	rm -fR ~/.vim ~/.vimrc ~/.vimrc.bepo
	ln -s `pwd`/vim ~/.vim
	ln -s ~/.vim/vimrc ~/.vimrc
	ln -s ~/.vim/vimrc.bepo ~/.vimrc.bepo

install-zsh: install-inputrc
	@echo ">>> zsh linking"
	rm -fR ~/.zlogin ~/.zlogout ~/.zsh ~/.zshrc ~/.zshenv
	ln -s `pwd`/zsh ~/.zsh
	ln -s ~/.zsh/env ~/.zshenv
	ln -s ~/.zsh/login ~/.zlogin
	ln -s ~/.zsh/logout ~/.zlogout
	ln -s ~/.zsh/rc ~/.zshrc

install-inputrc:
	rm -fR ~/.inputrc
	ln -s `pwd`/inputrc ~/.inputrc

install-xorg: install-fonts
	@echo ">>> X.org stuff linking"
	rm -fR ~/.Xdefaults ~/.xinitrc ~/.xinitrc.d ~/.colours
	ln -s `pwd`/Xdefaults ~/.Xdefaults
	ln -s `pwd`/xinitrc ~/.xinitrc
	ln -s `pwd`/xinitrc.d ~/.xinitrc.d
	ln -s `pwd`/colours ~/.colours

install-fonts:
	@echo ">>> fonts config linking"
	rm -fR ~/.fonts.conf
	ln -s `pwd`/fonts.conf ~/.fonts.conf

install-xmonad:
	@echo ">>> Xmonad linking… nerd stuff"
	rm -fR ~/.xmonad ~/.dzen_conkyrc
	ln -s `pwd`/xmonad ~/.xmonad

install-bin:
	@echo ">>> binaries linking"
	rm -fR ~/.bin
	ln -s `pwd`/bin ~/.bin

