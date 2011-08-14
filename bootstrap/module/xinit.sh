# xinit bootstrap module

_check() {
    check_or_exit xinit
}

_init() {
    return 0
}

_apply() {
    link_it ${TARGET_FOLDER} xinitrc.d
    link_it ${TARGET_FOLDER} xinitrc
}

if _check; then
    if _init; then
        _apply
    fi
fi

