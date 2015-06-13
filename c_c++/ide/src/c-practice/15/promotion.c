#include <stdio.h>
#include <stdint.h>
#include <assert.h>

int main(int argc, char *argv[]){
	int8_t a = -1;
    int8_t b = 255;
    int c = -1 - a;
    int d = 255 - b;
    assert(c == 0);
    assert(d == 256);
    assert(d == 0);
	return 0;
}
