#include <stdio.h>
struct {
	int a;
	char c;
}* test, m;

int main(int argc, char * argv){
	m.a = 83;
	test = &m;
	printf("%c", *test);
}
