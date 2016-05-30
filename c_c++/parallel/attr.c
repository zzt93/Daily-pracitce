#include <stdio.h>

char msg[] = "hello";
int thread_finished = 0;

int main() {
	
	int res;
	pthread_t a_thread;
	void *thread_result;
	pthread_attr_t thread_attr;
	res = pthread_attr_init(&thread_attr);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	res = pthread_create(&a_thread, &thread_attr, thread_f, (void*)msg);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
}
