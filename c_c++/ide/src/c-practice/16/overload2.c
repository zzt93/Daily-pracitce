#include "hello.h"
#include <stdio.h>
#include <stdarg.h>

int hello(const char *s, ...) {
    va_list v;
    va_start(v, s);
    const char * str = va_arg(v, const char *);
    va_end(v);
}

int main(int argc, char *argv[]){
    hello("a");
    hello("a", "b");
    hello("a", 1);
	return 0;
}
