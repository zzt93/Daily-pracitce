#include <stdio.h>

#define MAX 10

int main(int argc, char *argv[]){
	double ave = 0.0, A[MAX];
	int i;
	/*
	- A local copy of each list variable is made and initailized
	depending on the "op" (e.g. 0 for "+")
	- compiler finds standard reudction expressions containing
	"op" and uses them to update the local copy
	- local copies are reduced into a single value and combined
	with the original global value
	*/
	#pragma omp parallel for reduction (+: ave)
	for(i = 0; i < MAX; i++) {
		ave += A[i];
	}
	ave = ave / MAX;
	return 0;
}
