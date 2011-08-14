# Common bootstrap file
# Contains functions used in bootstrap.sh

# FIXME: This test is.. ugly
if test -f "./lib/sh/colors.sh"; then
    . "./lib/sh/colors.sh"
fi
check_cmd() {
    local command=$1
    echo -n "check for ${command}.. "
    if command -v $command >/dev/null; then
        echo "$(color green)ok$(color)"
        return 0
    else
        echo "$(color red)ko$(color)"
        echo "$(basename $0): ${command} not found." 1>&2
        return 1
    fi
}

check_or_exit() {
    local command=$1
    check_cmd $command || {
        exit 1
    }
}

note() {
    echo -n "$(color violet)note:$(color) $1"
}
warn() {
    echo -n "$(color yellow)warning:$(color) $1"
}
# Simple link
# link_simple source dest
link_simple() {
    local source=$1
    shift
    local dest=$1
    if ! test -e $source; then
        echo "$(basename $0): ${source} not found."
        return 1
    fi
    if test -e $dest; then
        backup_it $dest
        rm -R $dest
    fi
    echo -n "> Linking ${source} to ${dest}.."
    exec_status "ln -s $source $dest"
}

exec_status() {
    cmd="$1"
    if ! test -z "${ROOT_PRIVILEGE}"; then
        if test "${ROOT_PRIVILEGE}" = "yes"; then
            cmd="sudo $1"
        fi
    fi
    $cmd
    if test $? -eq 0; then
        echo "$(color green)ok$(color)"
    else
        echo "$(color red)ko$(color)"
    fi
}

# Link the current file or folder
# link_it target_folder file
# prefix = . on unix
link_it() {
    local target_folder=$1
    shift
    local file=$1
    shift
    if test "$1" = ""; then
        prefix=$FILE_PREFIX
    else
        prefix=$1
        shift
    fi
    if test "$1" = ""; then
        orig_file=$file
    else
        orig_file="$1"
    fi
    if ! check_it $target_folder $file; then
        return 1
    fi
    local target_file="${target_folder}/${prefix}${orig_file}"
    if test -e $target_file; then
        if ! test -L $target_file; then
            warn "${target_file} exists and is not a symbolic link.. "
            backup_it ${target_file}
        fi
        rm -R ${target_file}
    fi
    echo -n "> Linking $file to ${target_file}.. "
    local from="$PWD/$file"
    if ! test "${file}" = "${orig_file}"; then
        from="${file}"
    fi
    exec_status "ln -s ${from} ${target_file}"
}

# Backup a file or folder
backup_it() {
    if test -z "$NO_BACKUP"; then
        local target=$1
        echo "$(color blue)backing it up$(color)"
        if test -z $BACKUP_DIR; then
            BACKUP_DIR="/tmp/config-backup-$(date '+%Y%m%d')"
            warn "No \$BACKUP_DIR defined, using ${BACKUP_DIR} \n"
        fi
        if ! test -d $BACKUP_DIR; then
            mkdir $BACKUP_DIR
        fi
        local exec="cp"
        if test -d $target; then
            exec="cp -R"
        fi
        local cmd="${exec} ${target} ${BACKUP_DIR}/"
        $cmd
    else
        # no backup (--force)
        return 0
    fi
}

# Copy the current file or folder
# copy_it target_folder file
# prefix = . on unix
copy_it() {
    local target_folder=$1
    shift
    local file=$1
    shift
    if test "$1" = ""; then
        prefix=$FILE_PREFIX
    else
        prefix=$1
        shift
    fi
    if test "$1" = ""; then
        orig_file=$file
    else
        orig_file="$1"
    fi
    if ! check_it $target_folder $file; then
        return 1
    fi
    local target_file="${target_folder}/${prefix}${orig_file}"
    if test -e $target_file; then
        warn "${target_file} exists"
        if test -L $target_file; then
            echo
            warn "it is a symbolic link pointing to $(readlink ${target_file}).. "
            backup_it ${target_file}
        else
            echo -n ".. "
            backup_it ${target_file}
        fi
        rm -R ${target_file}
    fi
    echo -n "> Copying $file to ${target_file}.. "
    local from="$PWD/$file"
    if ! test "${file}" = "${orig_file}"; then
        from="${file}"
    fi
    exec_status "cp -R ${from} ${target_file}"
}

# Check if file and target exists
# check_it file target_folder
check_it() {
    local target_folder=$1
    shift
    local file=$1
    if ! test -e $file; then
        echo "$(color red)$(basename $0): ${file} not found.$(color)"
        return 1
    fi
    if ! test -d $target_folder; then
        echo "$(color red)$(basename $0): ${target_folder} doesn't exist or is not a folder$(color)"
        return 1
    fi
}

# Prepare the current file (user input here)
# prepare_it file [args]
prepare_it() {
    # Read input
    _read_it() {
        for element in $elements; do
            read -p "$element for $file: " "$element"
        done
    }
    _list_it() {
        for element in $elements; do
            eval rep='$'$element
            echo "$element: $rep"
        done
    }
    _write_it() {
        for element in $elements; do
            eval rep='$'$element
            cat $tmp_file | sed 's/${'"${element}"'}/'"${rep}"'/' > "${tmp_file}-new"
            mv "${tmp_file}-new" $tmp_file
        done
    }
    local file=$1
    shift
    local args=$1
    if ! test -e $file; then
        echo "$(basename $0): ${file} not found."
        return 1
    fi
    shift
    local tmp_file=$1
    if test -z $tmp_file; then
        tmp_file="/tmp/prepare_it-$file"
    fi
    elements="$(echo $args | tr "," " ")"
    cp $file $tmp_file
    while test -z $validate; do
        note "$file needs your input\n"
        _read_it
        note "You entered :\n"
        _list_it
        read -p "Are you sure ? [y/N]" yn
        case $yn in
            [Yy]* ) validate="go";;
            * ) echo ;;
        esac
    done
    _write_it
    cat $tmp_file
}

prepare_copy_it() {
    local target_folder=$1
    shift
    local file=$1
    shift
    local args=$1
    shift
    if test "$1" = ""; then
        prefix=$FILE_PREFIX
    else
        prefix=$1
    fi
    if ! check_it $target_folder $file; then
        return 1
    fi
    tmp_file="/tmp/prepare_it-$file"
    if test -f "${target_folder}/${prefix}${file}"; then
        warn "${target_folder}/${prefix}${file} already exists."
        read -p "Overwrite it ? [y/N]" yn
        case $yn in
            [Yy]* ) 
                    prepare_it $file $args $tmp_file
                    copy_it ${target_folder} ${tmp_file} ${prefix} ${file}
                    ;;
            * ) return 0;;
        esac
    fi
}

