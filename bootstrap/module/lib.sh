# lib bootstrap module
_check() {
    if test -z $LOCAL_PATH; then
        LOCAL_PATH="${TARGET_FOLDER}/.local"
    fi
    if ! test -e "${LOCAL_PATH}/lib"; then
        mkdir -p "${LOCAL_PATH}/lib"
    fi
}

_init() {
    # Nothing right now
    return 0
}

_apply() {
    cd lib
    for elt in *; do
        link_it ${LOCAL_PATH} "${elt}" "lib/"
    done
    cd -
}

if _check; then
    if _init; then
        _apply
    fi
fi
