#include <stdio.h>

struct {
    int a;
    char c;
} test = {
    2,
    4,
};

int main(){
    int a = test.a;
    printf("%d\n", a);
	return 0;
}
