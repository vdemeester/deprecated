# screen bootstrap module
_check() {
    check_or_exit screen
}

_init() {
    return 0
}

_apply() {
    link_it ${TARGET_FOLDER} screenrc
}

if _check; then
    if _init; then
        _apply
    fi
fi
