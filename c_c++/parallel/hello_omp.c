#include <stdio.h>
#include "omp.h"

// gcc -fopenmp hello_omp.c
int main(int argc, char *argv[]){
	#pragma omp parallel
	{
		int id = omp_get_thread_num();
		printf("hello world(%d)\n", id);
	}
	return 0;
}
