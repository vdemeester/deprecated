install: install-vim

install-vim:
	rm -fR ~/.vim ~/.vimrc ~/.vimrc.bepo
	ln -s `pwd`/vim ~/.vim
	ln -s ~/.vim/vimrc ~/.vimrc
	ln -s ~/.vim/vimrc.bepo ~/.vimrc.bepo
