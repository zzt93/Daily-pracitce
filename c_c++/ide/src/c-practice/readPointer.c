#include <stdio.h>

int main(int argc, char *argv[]) {
	void *a, *b, *c, *d;
	int e, f, g;
	scanf("%p%p%p%p%d%d%d", &a, &b, &c, &d, &e, &f, &g);//void **
	printf(" %p %p %p %p %d %d %d\n", a, b, c, d, e, f, g);
}
/*int main(int argc, char *argv[]) {
	void **a, **b, **c, **d;//error 1
	int e, f, g;
	scanf("%p%p%p%p%d%d%d", a, b, c, d, &e, &f, &g);//void ** , a is not initialized
	printf(" %p %p %p %p %d %d %d\n", *a, *b, *c, *d, e, f, g);
}*/