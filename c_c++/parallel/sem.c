#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

void *f(void *f);
char end[] = "end";

int main(int argc, char *argv[]){
	int res;
	pthread_t thread;
	res = pthread_create(&thread, NULL, f, argv[0]);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	return 0;
}

void *f(void *f) {
	sem_wait(&bin_sem);
	while(strcmp(, end) != 0) {
	}
}
