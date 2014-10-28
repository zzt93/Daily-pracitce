#include <stdio.h>

char * stringLiterals();
void disturb();

int main(int argc, char const *argv[])
{
	char *p ;
	int i;
	char c;
	long l;
	double d;
	float f;

	printf("%c\n", c);
	printf("%ld\n", l);
	printf("%lf\n", d);
	printf("%f\n", f);
	printf("%s\n", p);
	printf("%d\n", i);
	return 0;
}
