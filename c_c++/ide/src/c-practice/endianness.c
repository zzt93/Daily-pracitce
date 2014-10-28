#include <stdio.h>

int main(void){
    int x = 1;
    if(*(char *)&x == 1){
	 printf("little-endian\n");
    }
    else {
	printf("big-endian\n");
    }
    return 0;
}
