#include "var.h"
#include <stdio.h>

#define hello1(s) hello2(s, "default string")

void hello2(const char *s, const char* s2) {
    printf("hello2 %s and %s\n", s, s2);
}

#define hello(...) xglue(hello, PP_NARG(__VA_ARGS__))(__VA_ARGS__)

int main(int argc, char *argv[]){
    hello("a");
    hello("a", "b");
	return 0;
}
