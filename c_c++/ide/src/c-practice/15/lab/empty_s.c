#include <stdio.h>


struct {
    int t;
    int *s;
} empty, full;

int a;

int main(){
    printf("full is at %p", &full);
    printf("empty is %p", &empty);
    empty.t = 1;
	return 0;
}
