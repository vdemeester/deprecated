# --------------------------------------
# file:			~/.xinitrc
# author:		Vincent Demeester
# vim:nu:ai:si:et:ts=4:sw=4:ft=sh
# --------------------------------------
# Load .Xdefaults
xrdb -merge ~/.Xdefaults
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
# If were on Thinkpad (or at least having a Trackpoint)
if [[ -f /usr/bin/xinput ]]; then
    tp=`/usr/bin/xinput list | grep "TPPS/2 IBM TrackPoint"`
    echo $tp
    if [[ -n "$tp" ]]; then
        # Trackpoint is on, let's play with xinput
        # Vertical scrolling
        xinput set-int-prop "TPPS/2 IBM TrackPoint" "Evdev Wheel Emulation" 8 1
        xinput set-int-prop "TPPS/2 IBM TrackPoint" "Evdev Wheel Emulation Button" 8 2
        # Horizontal scrolling (with vertical enabled)
        xinput set-int-prop "TPPS/2 IBM TrackPoint" "Evdev Wheel Emulation Axes" 8 6 7 4 5
    fi
fi
# hide curson
unclutter -idle 5 &
# Start terminal emulation daemon
urxvtd -q -o -f
# Set the background (ugly right now, isn't it ?)
feh --bg-fill ~/pictures/wallpapers/current.jpg 
# Autostart programs
if [ -d $HOME/.xinitrc.d ] ; then
    for f in $HOME/.xinitrc.d/*.sh ; do
        [ -x "$f" ] && . "$f"
    done
    unset f
fi
if [[ -f /usr/bin/synergys && -f $HOME/.synergy.server.conf ]]; then
    /usr/bin/synergys --config $HOME/.synergy.server.conf
elif [[ -f /usr/bin/synergyc && -f $HOME/.synergy.client.conf ]]; then
    /usr/bin/synergyc # TODO: Content of $HOME/.synergy.client.conf
fi
# and start XMonad
exec ck-launch-session xmonad
#exec xmonad
