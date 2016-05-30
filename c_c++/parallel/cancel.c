#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

void *thread_f(void *arg);

int main(int argc, char *argv[]){
	
	int res;
	pthread_t a_thread;
	void *thread_result;

	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	
	return 0;
}

void *thread_f(void *f) {
	int i, res, j;
	puts("thread_f is running");
	res = pthread_setcancel
}
