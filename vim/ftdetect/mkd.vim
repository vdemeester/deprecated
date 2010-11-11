" Vim filetype detection file
augroup mkd
     au! BufRead,BufNewFile *.mkd   setfiletype mkd
     au! BufRead,BufNewFile *.md   setfiletype mkd
     au! BufRead,BufNewFile *.markdown   setfiletype mkd
augroup END
