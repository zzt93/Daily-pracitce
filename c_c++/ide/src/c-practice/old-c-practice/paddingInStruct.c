#include <stdio.h>

typedef struct {
	int i;
//	char str[8];//-->12
 	char *str;   //-->16  for the alignment of pointer in a contiguous sequence
} stru_12;

typedef struct {
	int i;
	char *str;
	char c;
} stru_13;

typedef struct {
	int i;
	char str[7];
} stru_11;

typedef struct {
	char *str;
	double d;
} stru_16;

typedef struct {
	char c;
	int i;
} stru_5;

stru_12 test12[3];
stru_13 test13;
stru_11 test11;
stru_16 test16;
stru_5 test5;
int main (int argc, char *argv[]) {
	printf("A test12 is %lu bytes, address is %p\n", sizeof test12, &test12[0]);
	printf("A test12 is %lu bytes, address is %p\n", sizeof test12, &test12[1]);
	printf("A test12 is %lu bytes, address is %p\n", sizeof test12, &test12[2]);
	printf("A test13 is %lu bytes, address is %p\n", sizeof test13, &test13);
	printf("A test11 is %lu bytes, address is %p\n", sizeof test11, &test11);
	printf("A test16 is %lu bytes, address is %p\n", sizeof test16, &test16);
	printf("A test5 is %lu bytes, address is %p\n", sizeof test5, &test5);
}
/*result:
**A struct is 16 bytes, address is 0x601050
**A struct is 24 bytes, address is 0x601080
**A struct is 12 bytes, address is 0x601070
**A struct is 16 bytes, address is 0x601060
 */
