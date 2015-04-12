#include <stdio.h>

int main(int argc, char const *argv[])
{
	int (*p)[13] ;
//	[Warning] assignment from incompatible pointer type [enabled by default]
	int a[12] = {1, 2};
	p = &a;
	printf("%d\n", (*p)[3]);
	return 0;
}
