#include <omp.h>
#include <stdio.h>

int main(int argc, char *argv[]){
	int c = 100;
	
	#pragma omp parallel for private(c)
	for(int i = 0; i < 10; i++) {
		c = 200;
		printf("%d\n", c);
	}
	printf("%d\n", c);


	puts("-----firstprivate-----");
	int a = 100;
	
	#pragma omp parallel for firstprivate(a)
	for(int i = 0; i < 10; i++) {
		printf("Thread ID: %d, %d: %d\n", omp_get_thread_num(), i, a);
		a = i;
	}
	printf("%d\n", a);

	puts("--------lastprivate-----");
	int tmp = 0;

	#pragma omp parallel 
	{
		#pragma omp for firstprivate(tmp) lastprivate(tmp)
		for(int i = 0; i < 1000; i++) {
			tmp += i;
			printf("%d, %d\n", omp_get_thread_num(), tmp);
		}
	}
	printf("%d\n", tmp);

	return 0;
}
