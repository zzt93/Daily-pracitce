#include <stdio.h>
#include <stdint.h>

int main(int argc, char *argv[]) {
	unsigned int a, b, c, res;
//	uint16_t d = 1;
	a = 0xFFFFFFFF; //Max value for a uint16_t
	b = 1;
	c = 2;

	res = a;
	res += b; //Overflow
	res -= c;
	printf("%x", res);
//	printf("%hd", d);
}
