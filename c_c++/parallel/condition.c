#include <stdio.h>
#include <pthread.h>
#include "errors.h"


typedef struct obj {
	pthread_mutex_t mutex;
	pthread_cond_t cond;
	int value;
} Obj;

Obj data = { PTHREAD_MUTEX_INITIALIZER, PTHREAD_COND_INITIALIZER, 0 };
int hibernation = 1;

void *f(void *arg) {
	sleep(hibernation);
}

int main(int argc, char *argv[]){
	int status;
	pthread_t wait_thread_id;
	struct timespec timeout;
	if (argc > 1) {
		hibernation = atoi(argv[1]);
	}
	timeout.sec = time(NULL) + 2;
	timeout.nsec = 0;
	pthread_create(&wait_thread_id, NULL, f, NULL);
	
	pthread_mutex_lock(&data.mutex);
	
	return 0;
}
