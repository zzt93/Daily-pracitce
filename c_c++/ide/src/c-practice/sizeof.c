/* 
 * zzt
 * 2014.7.11
 * sizeof.c--Program to tell byte size of the C variable 
 * 
 */
#include <stdio.h>

typedef struct {
	int a;
	char c;
	char *str;
	double d;
} TestS;

typedef union {
	double d;
	long l;
} TestU;

TestS *sp, s;
TestU *up, u;

int main(void) {
	int a[] = {1,2,3,4,5,6,7,8,9,0};
    printf("A Char is %lu bytes\n", sizeof( char ));
    printf("An int is %lu bytes\n", sizeof( int ));
    printf("A short is %lu bytes\n", sizeof( short ));
    printf("A long is %lu bytes\n", sizeof( long ));
    printf("A long long is %lu bytes\n\n", sizeof( long long ));

    printf("A void * is %lu bytes\n", sizeof( void *));
    printf("A char * is %lu bytes\n", sizeof( char *));
    printf("An int * is %lu bytes\n", sizeof( int *));
    printf("A short * is %lu bytes\n", sizeof( short *));
    printf("A long * is %lu bytes\n", sizeof( long *));
    printf("A long long * is %lu bytes\n\n", sizeof( long long *));

    printf("An unsigne Char is %lu bytes\n", sizeof( unsigned char ));
    printf("An unsigned int is %lu bytes\n", sizeof( unsigned int));
    printf("An unsigned short is %lu bytes\n", sizeof( unsigned short ));
    printf("An unsigned long is %lu bytes\n", sizeof( unsigned long ));
    printf("An unsigned long long is %lu bytes\n\n",
            sizeof( unsigned long long ));

    printf("float is %lu bytes\n", sizeof( float ));
    printf("A double is %lu bytes\n", sizeof( double ));
    printf("A long double is %lu bytes\n\n", sizeof( long double ));

    printf("A int array of ten elements is %lu bytes\n", sizeof a);

    printf("A pointer to struct is %lu bytes\n", sizeof sp);
    printf("A struct is %lu bytes\n", sizeof s);
    printf("A pointer to union is %lu bytes\n", sizeof up);
    printf("A union is %lu bytes\n", sizeof u);
    return 0;

}
