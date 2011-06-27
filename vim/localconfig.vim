" #############################################################################
" ### Documentation ###########################################################
" #############################################################################
" File:         localconfig.vim
" Version:      0.11
" Summary:      Loads vim configuration settings by hostname
" Maintainer:   Tim Skirvin <tskirvin@killfile.org>
" License:      BSD License
"
" This package tries to load additional configuration files, based on the
" hostname of your system.  Assuming you are 'host.domain.name', it will
" try to load the files 'domain.vim' and 'domain.host.vim' (in order)
" from the directory g:BASEDIR (set in this file).  If they're not there,
" then we'll continue without error.  Allows a user to have different
" configurations based on the hostnames they're working from.
"
" Also loads 'aliases.vim', 'aliases.domain.vim', and
" 'aliases.domain.host.vim'; these files contain aliases only.

" #############################################################################
" ### Variables ###############################################################
" #############################################################################

" Where are all of these configuration files kept?
let g:BASEDIR = "~/.config/vim"

" #############################################################################
" ### Functions ###############################################################
" #############################################################################

" Tries to load a file, and just fails silently if it doesn't.
function! LoadFileNoError(filename)
  let FILE=expand(a:filename)
  if filereadable(FILE)
    exe "source " FILE
  endif
endfunction

" Tries to load configuration files based on first domain name, then hostname.
function! LocalConfig()
  let HOST     = substitute ( hostname(), '\..*$', '', 'g' )
  let DOMAIN   = substitute ( $HOSTNAME, '^[^.]*\.', '', 'g')
  exec LoadFileNoError( g:BASEDIR . "/" . DOMAIN . ".vim" )
  exec LoadFileNoError( g:BASEDIR . "/" . HOST . "." . DOMAIN . ".vim" )
endfunction

" As with LocalConfig(), but loads aliases files, including aliases.vim
function! LocalAlias()
  let HOST     = substitute ( hostname(), '\..*$', '', 'g' )
  let DOMAIN   = substitute ( $HOSTNAME, '^[^.]*\.', '', 'g')
  exec LoadFileNoError( g:BASEDIR . "/aliases.vim" )
  exec LoadFileNoError( g:BASEDIR . "/aliases." . DOMAIN . ".vim" )
  exec LoadFileNoError( g:BASEDIR . "/aliases." . HOST . "." . DOMAIN . ".vim" )
endfunction

" #############################################################################
" ### main ####################################################################
" #############################################################################

" Actually invokes LocalConfig()
exec LocalConfig()
exec LocalAlias()

" #############################################################################
" ### Version Log #############################################################
" #############################################################################
" # 0.10        Thu Mar 24 14:14:55 CST 2005    tskirvin
" ### Initial version.  
" # 0.11        Thu Mar 24 14:59:28 CST 2005    tskirvin
" ### Now loads aliases.vim as well

