#!/bin/sh
# vim: syntax=sh
# Bootstraping script to install my config files
# Read arguments
while ! test -z "$1"; do
    case $1 in
        "--force"|"-f") 
            NO_BACKUP="yes" ;;
        *)
            MODULES="${MODULES} $1"
            NO_DEFAULT_MODULES="yes";;
    esac
    shift
done

# Default variable
BOOTSTRAP_FOLDER="bootstrap"
if test -z $TARGET_FOLDER; then
    TARGET_FOLDER="${HOME}"
fi
if test -z $BACKUP_DIR; then
    BACKUP_DIR=".backup_old_dot_files"
fi

command -v color >/dev/null || color() { true; }
command -v system_release >/dev/null || system_release() { true; }

# Get system information
UNAME=$(uname)
HOSTNAME=$(hostname -s)
DOMAINNAME=$(domainname)
RELEASE=$(system_release)

if test -f "${BOOTSTRAP_FOLDER}/common.sh"; then
    . "${BOOTSTRAP_FOLDER}/common.sh"
else
    echo "$(basename 0): ${BOOTSTRAP_FOLDER}/common.sh is missing.."
    exit 1
fi

echo
echo "$(color orange)                     __ _                   $(color)"
echo "$(color orange)     ___ ___  _ __  / _(_) __ _ ___         $(color)"
echo "$(color orange)    / __/ _ \| '_ \| |_| |/ _\` / __|        $(color)"
echo "$(color orange)   | (_| (_) | | | |  _| | (_| \__ \        $(color)"
echo "$(color orange)  (_)___\___/|_| |_|_| |_|\__, |___/        $(color)"
echo "$(color orange)                          |___/             $(color)"
echo
echo "System         : $(color blue)${UNAME}$(color)                       "
echo "System release : $(color red)${RELEASE}$(color)                     "
echo "Hostname       : $(color yellow)${HOSTNAME}$(color)                    "
echo "Domain Name    : $(color violet)${DOMAINNAME}$(color)                  "
echo

if test -f "${BOOTSTRAP_FOLDER}/${HOSTNAME}.sh"; then
    . "${BOOTSTRAP_FOLDER}/${HOSTNAME}.sh"
fi

echo
echo "Installing config files into user's home directory : \n$(color blue)${TARGET_FOLDER}$(color)"
echo 

if ! test -z "${NO_BACKUP}"; then
    echo "$(color red)Forcing : This means we are not doing any backup of existing file$(color)"
    echo
fi

if test -z "$FILE_PREFIX"; then
    FILE_PREFIX="."
fi
# Checking needed command, that should be available on *all* system
# Define some required module
REQUIRED_MODULES="scripts lib"
if test -z "$MODULES"; then
    # Default module
    MODULES="${REQUIRED_MODULES} zsh vim screen git fonts"
else
    if test "${NO_DEFAULT_MODULES}" = "yes"; then
        MODULES="${MODULES}"
    else
        MODULES="${REQUIRED_MODULES} ${MODULES}"
    fi
fi

echo
echo "Modules : ${MODULES}"
echo

read -p "[Enter] to start the bootstrap, [CTRL+C] to cancel.." go

# The real stuff happen here !
for module in ${MODULES}; do
    if test -f "${BOOTSTRAP_FOLDER}/module/${module}.sh"; then
        . "${BOOTSTRAP_FOLDER}/module/${module}.sh"
    else
        echo "$(color red)module ${module} does not exist$(color)"
    fi
done

if ! test -z "${ROOT_MODULES}"; then
    echo "$(color magenta)Some root module are defined.$(color)"
    check_or_exit sudo
    for module in ${ROOT_MODULES}; do
        if test -f "${BOOTSTRAP_FOLDER}/module/${module}.sh"; then
            ROOT_PRIVILEGE="yes" TARGET_FOLDER="/root" . "${BOOTSTRAP_FOLDER}/module/${module}.sh"
        else
            echo "$(color red)module ${module} does not exist$(color)"
        fi
    done
    ROOT_PRIVILEGE=""
fi
