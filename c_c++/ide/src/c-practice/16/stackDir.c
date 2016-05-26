#include <stdio.h>

void f(int *a) {
	int b = 0;
	printf("%s\n", &b > a ? "up" : "down");
}

int main(int argc, char *argv[]){
	int a = 0;
	f(&a);
	return 0;
}
