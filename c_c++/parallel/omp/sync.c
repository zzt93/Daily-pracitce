#include "omp.h"

void main() {
	float res;
	#pragma omp parallel
	{
		float B; int i, id, nthrds;
		id = omp_get_thread_num();
		nthreads = omp_get_num_threads();
		for(i = id; i < niters; i += nthreads) {
			B = big_job(i);
			#pragma omp critical
			{
				consume(B, res);
			}
		}
		#pragma omp atomic
		i+=B;
	}
}
