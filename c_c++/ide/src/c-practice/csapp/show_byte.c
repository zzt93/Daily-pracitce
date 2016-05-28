#include <stdio.h>
#include "show_byte.h"

int main(int argc, char *argv[]){
    int i = 0x1234;
    float f = (float)i;
    void *v = &i;
    show_type(i);
    show_type(f);
    show_type(v);
	return 0;
}
