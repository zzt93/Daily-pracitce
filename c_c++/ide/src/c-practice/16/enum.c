#include <stdio.h>

enum { A = 1, B, C = 4, D};

int main(int argc, char *argv[]){
	printf("%d, %d,\n", B, D);
	return 0;
}
