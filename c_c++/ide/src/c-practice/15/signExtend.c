#include <stdio.h>
#include <stdint.h>

int main(int argc, char *argv[]){
    int8_t a = -1;
    uint8_t ta = (uint8_t)a;
    uint32_t b = 1;
    b += ta;
    printf("%u", b);
	return 0;
}
