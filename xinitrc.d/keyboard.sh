#!/bin/sh
# Set Keyboard to bépo !
setxkbmap fr bepo-sbr
# Disable Capslock and map it to escape
setxkbmap -option compose:caps
# Enable CTRL+ALT+BACKSPACE to terminate X
setxkbmap -option terminate:escape
# call Xmodmap
xmodmap ~/.Xmodmap

