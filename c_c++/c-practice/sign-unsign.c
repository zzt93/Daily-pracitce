#include <stdio.h>

int main(int argc, char *argv[]){
	unsigned int a = -1;
//	int b = -20;
	char c = 5;
//	printf((a+b)>6 ? ">6" : "<=6");//signed and unsigned types have all operands promoted to unsigned types

	printf(a>c ? "a>c" : "a<=c");   //jae	.L2
}
