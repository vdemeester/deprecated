# scripts bootstrap module
SCRIPT_FOLDER="scripts"

_check() {
    if test -z $LOCAL_PATH; then
        LOCAL_PATH="${TARGET_FOLDER}/.local"
    fi
    if ! test -e "$LOCAL_PATH/bin"; then
        mkdir -p "$LOCAL_PATH/bin"
    fi
}

_init() {
    git submodule init ${SCRIPT_FOLDER}
    git submodule update ${SCRIPT_FOLDER}
}

_apply() {
    cd ${SCRIPT_FOLDER}
    for file in *; do
        if test "${file}" = "README.md"; then
            continue
        fi
        link_it ${LOCAL_PATH} "${file}" "bin/"
    done
    cd -
}

if _check; then
    if _init; then
        _apply
    fi
fi

