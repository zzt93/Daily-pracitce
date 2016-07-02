#include <stdio.h>

/**
implicit flush:
barrier
lock
*/
int main(int argc, char *argv[]){
	#pragma omp parallel 
	{
		int a = 0; // flush not guranteed
		//...
		#pragma omp flush(a)	
	}
	return 0;
}
