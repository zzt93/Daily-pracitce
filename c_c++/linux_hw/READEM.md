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

        ./ls .


-output:

        READEM.md
        wc.c
        test
        a.out
        ls
        ls.c


---------------

-input:

        ./ls -ali .


-output:
        type + permission|link| size|usr|group|time                  | inode 
        -rw-rw-r--        1     2638 zzt zzt Sun Mar 27 18:15:50 2016   6823973 READEM.md
        drwxr-xr-x        3     4096 zzt zzt Sun Mar 27 18:59:20 2016   6822142 .
        -rw-r--r--        1     1741 zzt zzt Wed Mar 23 20:38:42 2016   6823853 wc.c
        drwxrwxr-x        2     4096 zzt zzt Sun Mar 27 09:39:46 2016   7079201 test
        drwxrwxr-x        4     4096 zzt zzt Sun Mar 27 19:07:06 2016   6815798 ..
        -rwxrwxr-x        1    12048 zzt zzt Wed Mar 23 20:45:09 2016   6823896 a.out
        -rwxrwxr-x        1    18624 zzt zzt Sun Mar 27 18:59:20 2016   6823894 ls
        -rw-r--r--        1     6449 zzt zzt Sun Mar 27 18:59:06 2016   6823786 ls.c



------------------

-input:

        ./ls -lR .
        
        
-output:
        type + permission|link| size|usr|group|time
		-rw-rw-r--        1     2932 zzt zzt Sun Mar 27 19:13:57 2016  READEM.md
		-rw-r--r--        1     1741 zzt zzt Wed Mar 23 20:38:42 2016  wc.c
		drwxrwxr-x        2     4096 zzt zzt Sun Mar 27 09:39:46 2016  test
		test:
		-rw-rw-r--        1       47 zzt zzt Fri Mar 25 23:23:21 2016  b
		-rw-rw-r--        1        0 zzt zzt Wed Mar 23 23:14:18 2016  a

		-rwxrwxr-x        1    12048 zzt zzt Wed Mar 23 20:45:09 2016  a.out
		-rwxrwxr-x        1    18624 zzt zzt Sun Mar 27 18:59:20 2016  ls
		-rw-r--r--        1     6449 zzt zzt Sun Mar 27 18:59:06 2016  ls.c



#### Exit status
       0      if OK,

       1      if minor problems (e.g., cannot access subdirectory),


## Design

### wc

- implement two version of wc: the first read byte by byte, then count byte, word, line; the second read line by line, then analyse the line
- which version is better is not tested(For large file, reading line by line, can be a effective way)

### ls

- architecture design: pipe and filter
    - every filter is a function which corresponds to a command line opiton `[-*]`
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


## Comparison with source code

### wc
 - 在源码中，对于字符，或者说宽字符的类型都是通过宏或者typedef来定义的，这对于wc在不同系统平
台上的实现，或者对于不同字符编码集的字符宽度（Unicode-16或者ASCII），
这些是我的简单实现中没有涉及到的，我默认的字符都是以1Byte为单位⻓度
的，这些是没有详细考虑去实现的。
 - 源码考虑了一次传入多个文件，我没有。

### ls
 - 在我的实现中，这些信息都是取出后直接存放在变量中，然后打印出来。在源码中，
其定义了fileinfo的结构体，并且通过枚举类定义了与这些⽂件信息相关的类型, 以链表将多个文件的信息链接在一起，最后再输出。
 - 源码考虑了一次传入多个文件，我没有。
 - 源码考虑了先传入文件名，再传入参数，我默认最后一个参数为文件名