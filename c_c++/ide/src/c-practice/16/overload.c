#include <stdio.h>
#include "var.h"

void hello1(const char *s) {
    printf("hello1 %s\n", s);
}

void hello2(const char *s, const char* s2) {
    printf("hello2 %s and %s\n", s, s2);
}

#define hello(...) xglue(hello, PP_NARG(__VA_ARGS__))(__VA_ARGS__)

int main(int argc, char *argv[]){
    hello("a");
    hello("a", "b");
    //hello("a", 1);
	return 0;
}
