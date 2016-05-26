#include <stdio.h>

void f(int a) {
    char c = 'c';
    int array[15];
    long l = 1;
    array[1] = 1;
    printf("%c\n", c);
    printf("%ld\n", l);
    printf("%d\n", a);
    printf("%d\n", array[2]);
}

int main(int argc, char *argv[]){
    f(1);
	return 0;
}
