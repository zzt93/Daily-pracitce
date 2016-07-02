#include "omp.h"
#include <stdio.h>


int main(int argc, char *argv[]){
	int num_threads;
	omp_set_dynamic(0);

	omp_set_num_threads(omp_num_procs());
	omp_get_max_threads();
	#pragma omp parallel
	{
		#pragma omp single
		num_threads = omp_get_num_threads();
		
	}
	return 0;
}
