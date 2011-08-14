# ZSH bootstrap module
_check() {
    check_or_exit zsh
    check_or_exit dircolors
}

_init() {
    git submodule init zsh
    git submodule update zsh
}

_apply() {
    link_it ${TARGET_FOLDER} zsh
    ZSH_TARGET_FOLDER="${TARGET_FOLDER}/${FILE_PREFIX}zsh"
    link_simple ${ZSH_TARGET_FOLDER}/rc ${TARGET_FOLDER}/${FILE_PREFIX}zshrc
    link_simple ${ZSH_TARGET_FOLDER}/login ${TARGET_FOLDER}/${FILE_PREFIX}zlogin
    link_simple ${ZSH_TARGET_FOLDER}/logout ${TARGET_FOLDER}/${FILE_PREFIX}zlogout
    link_simple ${ZSH_TARGET_FOLDER}/env ${TARGET_FOLDER}/${FILE_PREFIX}zshenv
    link_it ${TARGET_FOLDER} dircolors
}

if _check; then
    if _init; then
        _apply
    fi
fi
