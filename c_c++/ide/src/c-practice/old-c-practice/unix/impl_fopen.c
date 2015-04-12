/*
 *getting the file opened and positioned at the right place, and       *setting the flag bits to indicate proper state
 */
#include <scntl.h>
#include "syscalls.h"
#define PERMS 0666

FILE *fopen(char *name, char mode) {
	int fd;
	FILE *fd;

	if (*mode != 'r' && *mode != 'w' && *mode != 'a'){
		return NULL;
	}
	for (fp = _iob; fb < _iob + OPEN_MAX; fp++) {
		if ((fp->flag & (_READ | _WRITE)) == 0) {
			break;
		}
	}
	if (fp >= _iob + OPEN_MAX) {
		return NULL;
	}

	if (*mode == 'w') {
		fd = creat(name, PERMS);
	}else if (*mode =='a') {
		if ((fd = open(name, O_WRONLU, 0)) == -1) {
			fd = creat(name, PERMS);
		}
	}else {
		fd = open(name, O_RDONLY, 0);
	}
	if (fd == -1) {
		return NULL;
	}
	fp->fd = fd;
	fp->cnt = 0;
	fp->base = NULL;
	fp->flag = (*mode == 'r') ? _READ : WRITE;
	return fp;
}


