#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

void *thread_function(void *f);

char message[] = "hello";

// gcc pthread.c -lpthread
int main(int argc, char *argv[]){
	int res;
	pthread_t a_thread;
	void *thread_result;
	res = pthread_create(&a_thread, NULL, thread_function, message);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	printf("Waiting for thread to finish\n");
	res = pthread_join(a_thread, &thread_result);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	printf("Thread joined, it returned %s\n", (char *)thread_result);
	printf("Message %s\n", message);
	return 0;
}

void *thread_function(void *f) {
	sleep(3);
	printf("argument is %s\n", (char *)f);
	pthread_exit("sleep 3 seconds");
}
