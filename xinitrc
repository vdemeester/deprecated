# --------------------------------------
# file:			~/.xinitrc
# author:		Vincent Demeester
# vim:nu:ai:si:et:ts=4:sw=4:ft=sh
# --------------------------------------
# Load .Xdefaults
xrdb -merge ~.Xdefaults
# Set Keyboard to bépo !
setxkbmap fr bepo
# Disable Capslock and map it to escape
setxkbmap -option caps:escape
# Enable CTRL+ALT+BACKSPACE to terminate X
setxkbmap -option terminate:escape
# call Xmodmap
xmodmap ~/.Xmodmap
# Set better defailt cursor for X
xsetroot -cursor_name left_ptr
# hide curson
unclutter -idle 5 &
# Start terminal emulation daemon
urxvtd -q -o -f
# Set the background (ugly right now, isn't it ?)
feh --bg-center $HOME/pictures/wallpapers/goro-debian-1680x1050.jpg
# and start XMonad
exec xmonad
