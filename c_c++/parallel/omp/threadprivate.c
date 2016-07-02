#include <omp.h>
#include <stdio.h>

int count = 0;
#pragma omp threadprivate(count);

int main(int argc, char *argv[]){
	#pragma omp parallel 
	{
		count ++;
	}
	// ...
	#pragma omp parallel
	{
		printf("%d\n", count);
	}
	return 0;
}
