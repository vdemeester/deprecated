#!/bin/sh
# Specific bootstrap stuff for kyushu host
if test -z "${MODULES}"; then
    MODULES="zsh vim pentadactyl git screen xorg xinit xmonad"
fi
#ROOT_MODULES="lib scripts zsh vim"
