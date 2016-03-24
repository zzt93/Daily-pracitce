## Sepcification
### wc
#### usage:
- wc file_name
#### output format:
- wc.c: byteCount, wordCount, lineCount
#### example:
- input:
```
./wc wc.c
```
- output:
```
wc.c: 1741, 222, 80
```
### ls
#### usage:
- ls [-a] [-l] [-i] [-d] [-R] file_name
- ls [-aildR] file_name
- the command options can be given in any order, and grouped togother in any order
#### options:
       -a
              do not ignore entries starting with .

       -d
              list directories themselves, not their contents

       -i
              print the index number of each file

       -l     use a long listing format
       
       -R
              list subdirectories recursively

#### output format:
every line is a file info line
#### example:
-input:
```
./ls .
```
-output:
```
READEM.md
wc.c
test
a.out
ls
ls.c
```

---------------
-input:
```
./ls -ali .
```
-output:
```
-rw-rw-r--  1 470 1000 1000 6823901 READEM.md
drwxr-xr-x  3 4096 1000 1000 6822142 .
-rw-r--r--  1 1741 1000 1000 6823853 wc.c
drwxrwxr-x  2 4096 1000 1000 7079201 test
drwxrwxr-x  4 4096 1000 1000 6815798 ..
-rwxrwxr-x  1 12048 1000 1000 6823896 wc
-rwxrwxr-x  1 17800 1000 1000 6823894 ls
-rw-r--r--  1 5569 1000 1000 6823786 ls.c
```

------------------
-input:
```
./ls -lR .
```
-output:
```
-rw-rw-r--  1 1577 1000 1000 READEM.md
-rw-r--r--  1 1741 1000 1000 wc.c
drwxrwxr-x  2 4096 1000 1000 test
test:
-rw-rw-r--  1 47 1000 1000 b
-rw-rw-r--  1  0 1000 1000 a

-rwxrwxr-x  1 12048 1000 1000 a.out
-rwxrwxr-x  1 17800 1000 1000 ls
-rw-r--r--  1 5569 1000 1000 ls.c
```

#### Exit status
       0      if OK,

       1      if minor problems (e.g., cannot access subdirectory),


## Design

### wc
- implement two version of wc: the first read byte by byte, then count byte, word, line; the second read line by line, then analyse the line
- which version is better is not tested(For large file, reading line by line, can be a effective way)

### ls
- architecture design: pipe and filter
   - every filter is a function which corresponds to a command line opiton `[-?]`
   - pipe is a shared line buffer

- refer to the stream design pattern:
   - filter: -a( and without -a), -d
   - reduce: -i, -l
   - map: -i, -l

- add some basic failure check:
   - not support this option/ invalid option
   - no such file or directory
   - too few arguments
   - invalid option