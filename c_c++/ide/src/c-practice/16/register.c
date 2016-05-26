#include <stdio.h>

void many_arguments(int f, int s, int t, int fo, int fi) {
}

void address_of_parameter(int f) {
	int *s = &f;
}

void plain_funtion(int f) {
}

int main(int argc, char *argv[]){
	many_arguments(1, 2, 3, 4, 5);
	return 0;
}
