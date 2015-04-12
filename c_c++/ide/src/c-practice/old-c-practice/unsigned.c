#include <stdio.h>

int main(int argc, char const *argv[])
{
	unsigned int i = -5;
	unsigned int j = 10;
	short k = -1;
	
	/*so while a may be unsigned, then the %d is asking to print the binary value as a signed value. 
	If you want to print it as a unsigned value, then use
    printf("%u",a);
     Most compilers will warn you about incompatible use of of parameters to printf
     -- so you could probably catch this by looking at all the warnings and fix it.  */	
	printf("%d\n", i);
	printf("%u\n", i);  //4294967291


	printf("%d\n", k);
	printf("%d\n", (unsigned int)k);  //-1?
	printf("%ld\n", (long)(unsigned int)k);  //4294967295

	for (; j > -1; --j)//the compiler know it can never be true (j > UINT_MAX ) loop so compiler cut it down
	{
		printf("%d\n", j);
	}
	return 0;
}
