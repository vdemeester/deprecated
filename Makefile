install: install-bin install-vim install-shells install-xorg \
		 install-xmonad

install-vim:
	rm -fR ~/.vim ~/.vimrc ~/.vimrc.bepo
	ln -s `pwd`/vim ~/.vim
	ln -s ~/.vim/vimrc ~/.vimrc
	ln -s ~/.vim/vimrc.bepo ~/.vimrc.bepo

install-shells: install-zsh install-inputrc

install-zsh:
	rm -fR ~/.zlogin ~/.zlogout ~/.zsh ~/.zshrc ~/.zshenv
	ln -s `pwd`/zsh ~/.zsh
	ln -s ~/.zsh/env ~/.zshenv
	ln -s ~/.zsh/login ~/.zlogin
	ln -s ~/.zsh/logout ~/.zlogout
	ln -s ~/.zsh/rc ~/.zshrc

install-inputrc:
	rm -fR ~/.inputrc
	ln -s `pwd`/inputrc ~/.inputrc

install-xorg:
	rm -fR ~/.Xdefaults ~/.xinitrc ~/.xinitrc.d ~/.colours
	ln -s `pwd`/Xdefaults ~/.Xdefaults
	ln -s `pwd`/xinitrc ~/.xinitrc
	ln -s `pwd`/xinitrc.d ~/.xinitrc.d
	ln -s `pwd`/colours ~/.colours

install-xmonad:
	rm -fR ~/.xmonad ~/.dzen_conkyrc
	ln -s `pwd`/xmonad ~/.xmonad

install-bin:
	rm -fR ~/.bin
	ln -s `pwd`/bin ~/.bin
