# bepo (linux) symbol install

_check() {
    grep -e "bepo-sbr" /usr/share/X11/xkb/symbols/fr 1&>2 2> /dev/null
    if $? == 0; then
        echo "Modified symbol file already present"
        return 1
    else
        if ! check_cmd "sudo"; then
            echo "sudo is not installed, can't apply the modified symbol file"
            return 1
        fi
    fi
}

_init() {
    return 0
}

_apply() {
    sudo mv /usr/share/X11/xkb/symbols/fr /usr/share/X11/xkb/symbols/fr.orig
    sudo cp xkb/symbols/fr /usr/share/X11/xkb/symbols/
}
