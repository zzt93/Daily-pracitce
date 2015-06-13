#include <stdio.h>
#include <string.h>

void foo(char *bar) {
    float f = 10.5;
    char c[8];
    printf("%f\n", f);
    strcpy(c, bar);
    printf("%f\n", f);
}

int main(int argc, char *argv[]){
    foo(argv[1]);
	return 0;
}
