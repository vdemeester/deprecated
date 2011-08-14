# xmonad bootstrap module
TO_INSTALL=""

_check() {
    check_or_exit ghc
    check_or_exit xmonad
    check_or_exit xmobar
}

_init() {
    git submodule init xmonad
    git submodule update xmonad
}

_apply() {
    link_it ${TARGET_FOLDER} xmonad
    link_simple ${TARGET_FOLDER}/${FILE_PREFIX}xmonad/xmobarrc ${TARGET_FOLDER}/${FILE_PREFIX}xmobarrc
}

if _check; then
    if _init; then
        _apply
    fi
fi
