#include <stdio.h>
#include <stddef.h>

int size = 100, get, i;
size_t ssize = 0;
void test1();
void test2();
int main(int argc, char *argv[]) {
    /*	scanf("%d", &get);
	test1();
	test2();
	printf("%d", i);*/
    ssize -= 1;
    if(ssize == -1){
        puts("sdf");
    }
}

void test1() {
	for (i = 1; i < size ; ++i) {
		if (get > 0) {
			break;
		}
	}
}

void test2() {
	for (i = 1; i < size && get <= 0; ++i) {
	}

}
