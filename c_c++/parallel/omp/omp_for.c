#include "omp.h"

/*
work sharing has implicit barrier at the end of 
construct

work sharing only allocate task to different threads,
they will not produce any thread.

work sharing construct must be enclosed by
a parallel region

single
for -- default 0:[0, n) 1:[n, 2n-1) ...
section
task

master -- is not work sharing

0 1 2
3 4 5
6 7 8


schedule(static [,chunk])
schedule(dynamic [,chunk])
schedule(guided [,chunk])
schedule(runtime [,chunk])
*/
void some() {
}

int big(){}

void main() {
	int n = 10;
	#pragma omp parallel
	{
		#pragma omp for
		for(i = 0; i < n; i++) {
			some();
		}
	}

	int i, j, A[10];
	j = 5;
	for(i = 0; i < 10; i++) {
		A[i] = big(j);
		j += 2;
	}

	/*
	make the loop iterations independent
	*/
	#pragma omp parallel for
	for(i = 0; i < 10; i++) {
		int new_j = 5 + 2*i;
		A[i] = big(new_j);
	}

	/*
	invalid usage
	int size;
	scanf("%d\n", &size);
	#pragma omp parallel for
	for(i = 0; i < size; i++) {
		printf("%d\n", i);
	}
	*/
}
