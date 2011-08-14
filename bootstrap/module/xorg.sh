# xorg bootstrap module
URXVT="urxvt"
TO_INSTALL=""

_check() {
    check_or_exit xrdb
    if ! check_cmd $URXVT 2>/dev/null; then
        warn "$URXVT is not installed."
        read -p "Would you install it ?" yn
        case $yn in
            [Yy]* ) TO_INSTALL="${TO_INSTALL} ${URXVT}";;
            * ) ;;
        esac
    fi
}

_init() {
    return 0
}

_apply() {
    link_it ${TARGET_FOLDER} colours
    link_it ${TARGET_FOLDER} Xdefaults
}

if _check; then
    if _init; then
        _apply
    fi
fi
