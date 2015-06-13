#include <stdio.h>
#include <string.h>

void foo(char *bar) {
    char c[8];
    strcpy(c, bar);
    printf("%s\n", c);
}

int main(int argc, char *argv[]){
    foo(argv[1]);
	return 0;
}
