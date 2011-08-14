# git bootstrap module
GIT_FLOW="git-flow"
GIT_EXTRAS="git-extras"
TO_INSTALL=""

_check() {
    check_or_exit git
    if ! check_cmd $GIT_FLOW 2>/dev/null; then
        warn "$GIT_FLOW is not installed."
        read -p "Would you install it ?" yn
        case $yn in
            [Yy]* ) TO_INSTALL="${TO_INSTALL} ${GIT_FLOW}";;
            * ) ;;
        esac
    fi
    if ! check_cmd $GIT_EXTRAS 2>/dev/null; then
        warn "$GIT_EXTRAS is not installed."
        read -p "Would you install it ?" yn
        case $yn in
            [Yy]* ) TO_INSTALL="${TO_INSTALL} ${GIT_EXTRAS}";;
            * ) ;;
        esac
    fi
}

_init() {
    if ! test -z "${TO_INSTALL}"; then
        echo -n "Installing ${TO_INSTALL} :"
        echo "$(color red) Not Implemented$(color)"
    fi
}

_apply() {
    vars="name,email,gh_user,gh_token"
    prepare_copy_it ${TARGET_FOLDER} gitconfig "${vars}" .
    link_it ${TARGET_FOLDER} gitignore_global
}

if _check; then
    if _init; then
        _apply
    fi
fi

