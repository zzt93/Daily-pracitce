#include <stdio.h>

int main(void)
{
	int i;

	printf("Enter a number: ");
	scanf("%d", &i);

	if( ( i % 2 ) == 0) printf("Even");
	if( ( i % 2 ) == 1) printf("Odd");

	return 0;
}	
