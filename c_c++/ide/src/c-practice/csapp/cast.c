#include <stdio.h>
#include "show_byte.h"

void castNegative() {
    short int v = 12345;
    unsigned short uv = (unsigned short)v;
    printf("v = %d, uv = %u\n", v, uv);
}

void castOverflow() {
    unsigned u = 0xffffffffu;
    int tu = (int)u;
    printf("u = %u, tu = %d\n", u, tu);
}

void expand() {
    short sx = -12345;
    unsigned short usx = (unsigned short)sx;
    int x = sx;
    unsigned int ux = usx;
    printf("sx  = %d:\t", sx);
    show_bytes((byte_pointer) &sx, sizeof(short));
    printf("usx = %u:\t", usx);
    show_bytes((byte_pointer) &usx, sizeof(unsigned short));
    printf("x   = %d:\t", x);
    show_bytes((byte_pointer) &x, sizeof(int));
    printf("ux  = %u:\t", ux);
    show_bytes((byte_pointer) &ux, sizeof(unsigned));
}

void expandCast() {
    short sx = 0xcfc7;
    //short s = -0xcfc7;
    unsigned int ui = (unsigned int)sx;
    printf("ui  = %u:\t", ui);
    show_bytes((byte_pointer) &ui, sizeof(unsigned));
}

void truncate() {
    int i = 0xf000f000;
    printf("i = %d:\t", i);
    show_type(i);
    short s = (short)i;
    printf("s = %d:\t", i);
    show_type(s);
}

int main(int argc, char argv[]) {
    castNegative();
    castOverflow();
    expand();
    expandCast();
    truncate();
}