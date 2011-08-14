                   __ _           
   ___ ___  _ __  / _(_) __ _ ___ 
  / __/ _ \| '_ \| |_| |/ _` / __|
 | (_| (_) | | | |  _| | (_| \__ \
(_)___\___/|_| |_|_| |_|\__, |___/
                        |___/     

This is my personal config file (or often called `dotfiles`) git repository.

The content of this repository is inspired and/or borrowed from a lot of github
user and community around the web. I'll try to keep the source in the file to
let you dig in.

# About

These config files are meant to work on GNU/Linux (mainly used on Debian-based
host but should work on all distribution) and on Mac OS X. Most of it should 
work on other *nixes too.

# Features

This dotfiles are mainly covering :

- vim (git://github.com/vdemeester/vim-config.git)
- xmonad (git://github.com/vdemeester/xmonad-config.git)
- zsh
- git
- mutt
- Xorg stuff
- urxvt
- mpd & ncmpcpp
- GNU screen
- dev. languages : java, haskell, python, ruby

There is also the bin/ directory where useful scripts and `command` are (
borrowed or created).

# Setup

You can adapt this process to you specific needs; I usually do something like
this :

    # cloning
    git clone git://github.com/vdemeester/configs.git some/directory

    # bootstraping
    # The bootstrap.sh script will need your input
    cd some/directory
    sh ./bootstrap.sh

The `bootstrap.sh` script takes care of the submodule init and update and will
check the status of the current repository (and its submodule). You can init
the submodule by hand :

    # init
    git submodule init
    # update
    git submodule update

# Notes

I replaced my Makefile by a sh script as I might not have a make command
available on the hardware I'm willing to put my config files.

I am mainly running Debian servers and Debian based laptop or desktop (debian
or ubuntu) and a Mac OS X system (which is not started often, but anyway). To 
know more about my hardware configuration, you could take a look at my [website][]. I use the same config files at work and at home.

[website]:  http://shortbrain.org
