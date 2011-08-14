# VIM bootstrap module
VIM="vim"
GVIM="gvim"
MVIM="mvim"

_check() {
    check_or_exit $VIM
    if ! check_cmd $GVIM 2>/dev/null; then
        # We are going to try mvim (Mac OS X)
        if ! check_cmd $MVIM 2>/dev/null; then
            warn "$GVIM or $MVIM not in the path. The configuration provided might not work.\n"
            read -p "Are you sure to continue for vim module ? [y/N]" yn
            case $yn in
                [Yy]* ) return 0;;
                * ) return 1;;
            esac
        fi
    fi
}

_init() {
    git submodule init vim
    # FIXME Do I need do run "cd vim && git submodule init && git submodule update"
    git submodule update vim
}

_apply() {
    link_it ${TARGET_FOLDER} vim
    TARGET_PREFIXED="${TARGET_FOLDER}/${FILE_PREFIX}"
    link_simple ${TARGET_PREFIXED}vim/vimrc ${TARGET_PREFIXED}vimrc
}

if _check; then
    if _init; then
        _apply
    fi
fi
