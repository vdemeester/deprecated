" No VI compatibility
set nocompatible
" default encoding
set encoding=utf-8
set fileencoding=utf-8
set history=600
filetype plugin on
"filetype indent on
set foldenable
set foldmethod=marker
set foldcolumn=1
set autoread
colorscheme two2tango
if !has("gui_running")
    set mouse=a
endif
if has("gui_running")
	set guifont=Consolas\ 9
	set guioptions=gitac
	set foldcolumn=2
endif
set mousehide

set nu
set wildmenu
set wildmode=list:longest,list:full
set wildignore=.svn,CVS,.git,*.o,*.a,*.class,*.mo,*.la,*.so,*.obj,*.swp,*.jpg,*.png,*.xpm,*.gif,*.pyc,*~
" some filetypes got lower priority
set su=.h,.bak,~,.o,.info,.swp,.obj

"mapleader 
let mapleader = ","
let g:mapleader = ","
" fast reloading & editing
map <leader>s :source ~/.vimrc<cr>
map <leader>e :e! ~/.vimrc<cr>
" When .vimrc is editited, reoad it automatically
autocmd! bufwritepost .vimrc source ~/.vimrc
autocmd! bufwritepost .gvimrc source ~/.gvimrc

" raccourci classique pour sauvegarder
nmap <c-s> :w<CR>
imap <c-s> <Esc>:w<CR>a
nmap <leader>w :w!<CR>

" listchar	    
set listchars=nbsp:·,tab:☞\ ,trail:¤,extends:>,precedes:<,eol:¬

syntax enable
" colorise les nbsp    
highlight NbSp ctermbg=lightgray guibg=#1A1A1A
match NbSp /\%xa0/

" Default --> tab width & co
set tabstop=4
set shiftwidth=4

" Syntax switch
map <leader>h :set syntax=html<cr>
map <leader>j :set syntax=javascript<cr>
map <leader>c :set syntax=css<cr>

" Fileformat
set ffs=unix,dos,mac
nmap <leader>fd :se ff=dos<cr>
nmap <leader>fu :se ff=unix<cr>

" Vim user interface
" Set 7 lines to the cursor
set so=7

" You can change buffer without saving the current
set hid
set backspace=eol,start,indent

set whichwrap+=<,>,h

" search
set incsearch
set ignorecase
" and the magic
set magic

set showmatch

set hlsearch

" Status line
set laststatus=2

""""""""""""""""""""""""""""""
" Visual
""""""""""""""""""""""""""""""
" From an idea by Michael Naumann
function! VisualSearch(direction) range
  let l:saved_reg = @"
  execute "normal! vgvy"
  let l:pattern = escape(@", '\\/.*$^~[]')
  let l:pattern = substitute(l:pattern, "\n$", "", "")
  if a:direction == 'b'
    execute "normal ?" . l:pattern . "^M"
  else
    execute "normal /" . l:pattern . "^M"
  endif
  let @/ = l:pattern
  let @" = l:saved_reg
endfunction

"Basically you press * or # to search for the current selection !! Really useful
vnoremap <silent> * :call VisualSearch('f')<CR>
vnoremap <silent> # :call VisualSearch('b')<CR>

map <leader>tn :tabnew %<cr>
map <leader>te :tabedit
map <leader>tc :tabclose<cr>
map <leader>tm :tabmove
try
  set switchbuf=usetab
  set stal=1
catch
endtry

" Switch current dir (file ? Yep boy !!)
map <leader>cd :cd %:p:h<cr>

" Map auto complete of (, [, {
inoremap $1 ()<esc>:let leavechar=")"<cr>i
inoremap $2 []<esc>:let leavechar="]"<cr>i
inoremap $3 {}<esc>:let leavechar="}"<cr>i

" Abbrevs
iab xname Vincent Demeester
iab xmail vincent@demeester.fr
iab xdate <c-r>=strftime("%d/%m/%Y %H:%M:%S")<cr>

func! DeleteTrailingWS()
  exe "normal mz"
  %s/\s\+$//ge
  exe "normal `z"
endfunc
autocmd BufWrite *.py :call DeleteTrailingWS()

func! Cwd()
  let cwd = getcwd()
  return "e " . cwd 
endfunc

func! CurrentFileDir(cmd)
  return a:cmd . " " . expand("%:p:h") . "/"
endfunc

" Command(line) shortcuts
cno $h e ~/
cno $p e ~/Projects/
cno $j e ./

cno $c e <C-\>eCurrentFileDir("e")<cr>

cno $tc <C-\>eCurrentFileDir("tabnew")<cr>
cno $th tabnew ~/
cno $tp tabnew ~/Projects/

" Bash like
cnoremap <C-A> <Home>
cnoremap <C-E> <End>
cnoremap <C-K> <C-U>

"Quick find & switch to buffer
map <c-q> :sb 
map <leader>q :e ~/buffer<cr>

"Restore cursor to file position in previous editing session
set viminfo='10,\"100,:20,%,n~/.viminfo
au BufReadPost * if line("'\"") > 0|if line("'\"") <= line("$")|exe("norm '\"")|else|exe "norm $"|endif|endif

" Turn backup off
set nobackup
set nowb
set noswapfile

" folding
set nofen
set fdl=0

" Indent (auto, smart)
set ai
set si

" lazy code folding / unfolding
noremap <Tab> za
noremap <S-Tab> zA

" keep the current selection when indenting (thanks cbus)
vnoremap < <gv
vnoremap > >gv

" Filetype related

" {{{
" Python
autocmd FileType python map <buffer> <leader><space> :w!<cr>:!python %<cr>
" autocmd FileType python so ~/vim_local/plugin/python_fold.vim
autocmd FileType python set tabstop=4
autocmd FileType python set shiftwidth=4
autocmd FileType python set expandtab

"Set some bindings up for 'compile' of python
autocmd FileType python set makeprg=python\ -c\ \"import\ py_compile,sys;\ sys.stderr=sys.stdout;\ py_compile.compile(r'%')\"
autocmd FileType python set efm=%C\ %.%#,%A\ \ File\ \"%f\"\\,\ line\ %l%.%#,%Z%[%^\ ]%\\@=%m

" Python iMaps
au FileType python set cindent
au FileType python inoremap <buffer> $r return
au FileType python inoremap <buffer> $s self
au FileType python inoremap <buffer> $c ##<cr>#<space><cr>#<esc>kla
au FileType python inoremap <buffer> $i import
au FileType python inoremap <buffer> $p print
au FileType python inoremap <buffer> $d """<cr>"""<esc>O

autocmd FileType vim set nofen

" }}}
" * Html
autocmd FileType html set tabstop=4
autocmd FileType html set shiftwidth=4
autocmd FileType html set expandtab

" TODO/Taskpaper
au BufNewFile,BufRead *.todo setf taskpaper

" Vala filetype
autocmd BufRead *.vala set efm=%f:%l.%c-%[%^:]%#:\ %t%[%^:]%#:\ %m
autocmd BufRead *.vapi set efm=%f:%l.%c-%[%^:]%#:\ %t%[%^:]%#:\ %m
au BufRead,BufNewFile *.vala            setfiletype vala
au BufRead,BufNewFile *.vapi            setfiletype vala

" BÉPO power
source ~/.vimrc.bepo

" vim: set foldmethod=marker 
