#include <stdio.h>

int main(int argc, char *argv[]){
	#pragma omp parallel num_threads(2)
	{
		#pragma omp sections
		{
		#pragma omp section
			x();
		}
		//...
		#pragma omp sections
		{
		#pragma omp section
			x();
		#pragma omp section
			y();		
		#pragma omp section
			z();
		}
	}
	return 0;
}
