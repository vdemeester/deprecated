" Vim syntax file
" Language:	pTaskpaper (http://hogbaysoftware.com/projects/taskpaper)
" Maintainer:	Vincent Demeeser <vincent@demeester.fr>
" Maintainer:	David O'Callaghan <david.ocallaghan@cs.tcd.ie>
" URL:		http://www.cs.tcd.ie/David.OCallaghan/taskpaper.vim/
" Version:	1
" Last Change:  2007 Sep 25

if version < 600
  syntax clear
elseif exists("b:current_syntax")
  finish
endif

if version < 508
  command! -nargs=+ HiLink hi link <args>
else
  command! -nargs=+ HiLink hi def link <args>
endif

syn case ignore

syn match  taskpaperProject       /^.\+:\s*$/
syn match  taskpaperLineContinue ".$" contained
syn match  taskpaperListItem  "^\s*[-+]\s\+" 
syn match  taskpaperSameItem  "^\s*[>]\s\+" 
syn match  taskpaperWho  "@[A-Za-z0-9_]\+"
syn match  taskpaperContext  "#[A-Za-z0-9_]\+"
syn match  taskpaperFailed "^\s*[-+]\s\+.*#[Ff]ailed.*$"
syn match  taskpaperDone "^\s*[-+]\s\+.*#[Dd]one.*$"
syn match  taskpaperDate "([0-9]\{2}\/[0-9]\{2}\/[0-9]\{4})"

syn region taskpaperProjectFold start=/^.\+:\s*$/ end=/^\s*$/ transparent fold

syn sync fromstart

"highlighting for Taskpaper groups
HiLink taskpaperListItem       Identifier
HiLink taskpaperContext        Identifier
HiLink taskpaperSameItem       Constant
HiLink taskpaperProject        Title
HiLink taskpaperFailed	       Error
HiLink taskpaperWho	       Directory
HiLink taskpaperDone           NonText
HiLink taskpaperDate           Operator

let b:current_syntax = "taskpaper"

delcommand HiLink
" vim: ts=8
