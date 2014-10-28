#include <stdio.h>
#include <stdlib.h>

int main (int argc, char *argv[]) {
	size_t max = 100;
	char *buf = malloc(sizeof(char) * 100);
	size_t len = getline(&buf, &max, stdin);
	//	int i;
	//	scanf("%d", &i);
	printf("length %zu: %s", len, buf);
}
