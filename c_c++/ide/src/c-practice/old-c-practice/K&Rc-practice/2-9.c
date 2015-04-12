#include <stdio.h>
#include <math.h>

int bitcount(unsigned x);

int main(int argc, char const *argv[])
{
	unsigned int i = -1;
	printf("%d\n", bitcount(-2));
	printf("%d\n", i);
	return 0;
}

int bitcount(unsigned x) {//it can also count number<0
	int b = 0;
	while (x){//--x is wrong for first it calculate --x(x change too), then x&(--x) equals x&x
		x = (x & (x-1));
		b ++;
	}
	return b;
}
