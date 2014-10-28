#include <stdio.h>
struct test {
	int i ;
	char *p;
} *a, *b;
int main(int argc, char *argv[]) {
	(*a).i = 1;
	(*a).p = "abc";
	b->i = 2;
	b->p = "abcd";
	return 0;
}
