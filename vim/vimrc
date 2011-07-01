" .vimrc file of Vincent Demeester
" I tried to comment it as much as I could.
"
" Load pathogen (if present)
" This has to be before filetype plugin on as pathogen won't work then.
runtime pathogen_init
" Drop compatibility
set nocompatible

" Set default encoding to utf-8
set encoding=utf-8
set fileencoding=utf-8
" Remembering the 600 last command. Vim default is 20.
set history=600
" Enable filetype detection.
filetype plugin on
"filetype indent on
if has("gui_running")
    " Simple GUI, almost similar to console version
    " i > Use Vim icon
    " t > tearoff menu items. TODO learn what it means
    " a > autoselect (means that vim might try to share selection with system.
    " c > Use console dialogs
    set guioptions=itac
    " allocate 2 pixels for folding column (even if no folding present)
    set foldcolumn=2
    " Light background on GUI version
    set background=light
else
    " Dark background on CLI version (as terminal background usually dark)
    set background=dark
    " Enable mouse support in CLI
    set mouse=a
endif
" Colorscheme for both dark and light background
colorscheme solarized
" Hightlight the line where the cursor is.
set cursorline
" Hide the mouse when writing/using vim (who needs a mouse anyway !)
set mousehide

" Highlight column that are listed (80 and 120)
if v:version > '702'
    set colorcolumn=80,120
endif

set wildmenu
set wildmode=list:longest,list:full
set wildignore=.svn,CVS,.git,*.o,*.a,*.class,*.mo,*.la,*.so,*.obj,*.swp,*.jpg,*.png,*.xpm,*.gif,*.pyc,*~
" some filetypes got lower priority
set su=.h,.bak,~,.o,.info,.swp,.obj

" Remaping mapleader (default is \)
let mapleader = ","
let g:mapleader = ","

nnoremap <Leader><Leader> <Leader>

" fast reloading & editing
map <leader>s :source $MYVIMRC<cr>
map <leader>e :e! $MYVIMRC<cr>

" When .vimrc is editited, reoad it automatically
autocmd! bufwritepost .vimrc source $MYVIMRC

nmap <leader>w :w!<CR>
nmap <leader>l :set list!<CR>
nmap <leader>n :set nu!<CR>

" listchar      
set listchars=nbsp:·,tab:☞\ ,trail:¤,extends:>,precedes:<,eol:¬

syntax enable
" colorise les nbsp       
highlight NbSp ctermbg=lightgray guibg=#1A1A1A
match NbSp /\%xa0/

" Fileformat
set ffs=unix,dos,mac
nmap <leader>fd :se ff=dos<cr>
nmap <leader>fu :se ff=unix<cr>

" Vim user interface
" Set 7 lines to the cursor
set so=5

" You can change buffer without saving the current
set hid
set backspace=eol,start,indent

"set whichwrap+=<,>,h "?

" search
set incsearch
set ignorecase
" and the magic
set magic "?
set showmatch
set hlsearch

" Status line
set laststatus=2
set statusline=%f\ %m\ %h%r%=[%{&encoding}\ %{&fileformat}\ %{strlen(&ft)?&ft:'none'}\ %{getfperm(@%)}]\ 0x%B\ %12.(%c:%l/%L%)

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

" Switch current dir (file ? Yep boy !!)
map <leader>cd :cd %:p:h<cr>

" Abbrevs 
" TODO Move them elsewhere
iab xname Vincent Demeester
iab xmail vincent@demeester.fr
iab xdate <c-r>=strftime("%d/%m/%Y %H:%M:%S")<cr>

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

" keep the current selection when indenting (thanks cbus)
vnoremap < <gv
vnoremap > >gv

" tabs default stuff
set tabstop=4
set softtabstop=4
set shiftwidth=4
set expandtab

let hostfile=$HOME . '/.vim/vimrc-' . hostname()
if filereadable(hostfile)
    exe 'source ' . hostfile
endif
" FIXME detect bepo keymap ?
exe 'source $HOME/.vim/vimrc-bepo'
" vim: set foldmethod=marker
