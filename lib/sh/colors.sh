color() {
    local str="\033["
    local foreground=$1
    case $foreground in
        "green"|"g" ) echo -n "${str}32m";;
        "red"|"r" ) echo -n "${str}31m";;
        "orange"|"o" ) echo -n "${str}1;31m";;
        "blue"|"b" ) echo -n "${str}34m";;
        "yellow"|"y" ) echo -n "${str}33m";;
        "magenta"|"m" ) echo -n "${str}35m";;
        "violet"|"v" ) echo -n "${str}1;35m";;
        * ) echo -n "${str}0m";;
    esac
}

