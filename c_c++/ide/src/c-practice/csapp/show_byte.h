#ifndef __SHOW_BYTE_H__
#define __SHOW_BYTE_H__

#include <stdio.h>

typedef unsigned char *byte_pointer;

static
void show_bytes(byte_pointer p, int size) {
    int i;
    for (i = 0; i < size; i++) {
        printf("%.2x ", p[i]);
    }
    puts("");
}

#define show_type(type) {                               \
        show_bytes((byte_pointer)&type, sizeof type);    \
    }


#endif /* __SHOW_BYTE_H__ */