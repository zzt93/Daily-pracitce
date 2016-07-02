#include <stdio.h>

int main(int argc, char *argv[]){
	#pragma omp parallel shared (A, B, C) private(id)
	{
		id = omp_get_thread__num();
		A[id] = big_calc1(id);
		#pragma omp barrier
		#pragma omp for
		for(i = 0; i < N; i++) {
			C[i] = big_calc3(i, A);
		}
		#pragma omp for nowait
		for(i = 0; i < N; i++) {
			B[i] = big_calc2(C, i);
		}
		// no implicit barrier because 'nowait'
	}
	return 0;
}
