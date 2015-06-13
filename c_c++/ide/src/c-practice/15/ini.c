#include <stdio.h>

/*
struct a {
    unsigned char b: 1;
    unsigned char bb: 1;
    char str[2];
    int c;
} test = {.b = 1};

int main(int argc, char *argv[]){
    printf("%u, %u, %d, %d, %d", test.b, test.bb, test.c, test.str[0], test.str[1]);
	return 0;
}
*/
int add(int a, int b, int c, int d, int e, int f) {
    printf("%d %d\n", a, b);//no error only a warnig here if we give printf only one parameter
    return a + b + c + d + e + f;
}

int main(int argc, char *argv[])
{
    add(1, 2, 3, 4, 5, 6);
    return 0;
}
