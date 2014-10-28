#include <stdio.h>
 
int main(void) {
	int arr[4][4];
	int n;
	int x, y;
	sscanf("4", "%d", &n);
	int vla[n][n];
 
	x = y = 0;
	x = sizeof(arr[y=2]);
	printf("after sizeof array x, y are %d, %d\n", x, y);
 
	x = y = 0;
	x = sizeof(vla[y=3]);
	printf("after sizeof vla x, y are %d, %d\n", x, y);
 
}
