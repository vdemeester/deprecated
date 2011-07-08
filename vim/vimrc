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
" Backup & Swap file
" The point here is : tell vim not to write this kind of file in the current 
" folder (and even deactivate it if possible).
set nobackup
set nowb
set noswapfile

" Enable filetype detection.
filetype on
filetype plugin on
filetype indent on
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
" Set Mininal screen lines above/below the cursor
set scrolloff=5
" Do not forbid switching buffer in case of modified buffer
set hidden
" Enable all backspace stuff 
set backspace=eol,start,indent
" Show matches when typing the pattern
set incsearch
" Ignore case by default in search
" Possible to re-enabling it using \C in the search pattern
set ignorecase
" Highlight matches of search by default - use :nohlsearch for hiding it
set hlsearch
" Magic mode (in pattern matching)
" Possible to disable it using \M (read help for more informations)
set magic
" Show the matche pair (for(,[,{,<,…)
set showmatch
" Always show the statusline and commandline
set laststatus=2
" Change the default statusline
set statusline=%f\ %m\ %{fugitive#statusline()}\ %h%r%=[%{&encoding}\ %{&fileformat}\ %{strlen(&ft)?&ft:'none'}\ %{getfperm(@%)}]\ 0x%B\ %12.(%c:%l/%L%)

" Highlight column that are listed (80 and 120)
if v:version > '702'
    set colorcolumn=80,120
endif

" Enhancing command-line completion (with options)
set wildmenu
set wildmode=list:longest,list:full
set wildignore=.svn,CVS,.git,*.o,*.a,*.class,*.mo,*.la,*.so,*.obj,*.swp,*.jpg,*.png,*.xpm,*.gif,*.pyc,*~
" some filetypes got lower priority
set su=.h,.bak,~,.o,.info,.swp,.obj

" Set tab/space default behvior. The rest is going to be set by filetype
set tabstop=4
set softtabstop=4
set shiftwidth=4
set expandtab

" Remaping mapleader (default is \)
let mapleader = ","
let g:mapleader = ","

" fast reloading & editing .vimrc file
map <leader>s :source $MYVIMRC<cr>
map <leader>e :e! $MYVIMRC<cr>
" When .vimrc is editited, reoad it automatically
autocmd! bufwritepost .vimrc source $MYVIMRC

" Quick access to useful funtions
" - Quick saving
nmap <leader>w :w!<CR>
" - Toggle typo characters
nmap <leader>tl :set list!<CR>
" - Toggle line numbers
nmap <leader>tn :set number!<CR>
" - Toggle relative numbers (fun)
nmap <leader>tN :set relativenumber!<CR>
" - Spell checking
nmap <leader>ts :set spell!<CR>
" - Quick change directory
map <leader>cd :cd %:p:h<cr>
" - Toggle Gundo view
nmap <leader>u :GundoToggle<cr>

" Toggle quicklist window
nmap <leader>tq :QFix<CR>
" Customization : Preview pane (diff) at the bottom of the current buffer
let g:gundo_preview_bottom = 1
" Customization of unimpaired keymap (not upstream right now)
let g:unimpaired_leader_next = ')'
let g:unimpaired_leader_prev = '('

" Customize fugitive
if has("autocmd")
    autocmd BufReadPost fugitive://* set bufhidden=delete
endif

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

" keep the current selection when indenting (thanks cbus)
vnoremap < <gv
vnoremap > >gv

" Finaly source `hostname` specific configuration file if existing
let hostfile=$HOME . '/.vim/vimrc-' . hostname()
if filereadable(hostfile)
    exe 'source ' . hostfile
endif
" vim: set foldmethod=marker
