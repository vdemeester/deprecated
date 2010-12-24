install: install-vim install-zsh

install-vim:
	rm -fR ~/.vim ~/.vimrc ~/.vimrc.bepo
	ln -s `pwd`/vim ~/.vim
	ln -s ~/.vim/vimrc ~/.vimrc
	ln -s ~/.vim/vimrc.bepo ~/.vimrc.bepo

install-zsh:
	rm -fR ~/.zlogin ~/.zlogout ~/.zsh ~/.zshrc ~/.zshenv
	ln -s `pwd`/zsh ~/.zsh
	ln -s ~/.zsh/env ~/.zshenv
	ln -s ~/.zsh/login ~/.zlogin
	ln -s ~/.zsh/logout ~/.zlogout
	ln -s ~/.zsh/rc ~/.zshrc
