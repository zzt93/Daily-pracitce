#include <stdio.h>

typedef unsigned char *byte_pointer;

void show_byte(byte_pointer p, int size) {
    int i;
    for (i = 0; i < size; i++) {
        printf("%.2x ", p[i]);
    }
    puts("");
}

#define show_type(type) {                               \
        show_byte((byte_pointer)&type, sizeof type);    \
    }

int main(int argc, char *argv[]){
    int i = 0x1234;
    float f = (float)i;
    void *v = &i;
    show_type(i);
    show_type(f);
    show_type(v);
	return 0;
}
