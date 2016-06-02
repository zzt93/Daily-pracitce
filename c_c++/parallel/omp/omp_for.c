#include "omp.h"

/*
work sharing has implicit barrier at the end of 
construct

work sharing construct must be enclosed by
a parallel region


0 1 2
3 4 5
6 7 8
*/
void main() {
	int n = 10;
	#pragma omp parallel
	{
		#pragma omp for
		for(i = 0; i < n; i++) {
			some();
		}
	}
}
