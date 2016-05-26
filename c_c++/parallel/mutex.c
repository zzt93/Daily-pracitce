#include <stdio.h>

void *f(void *f);
char work_area[1024];

int main(int argc, char *argv[]){
	int res;
	pthread_mutex_t work_mutex;
	res = pthread_mutex_init(&work_mutex);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	res = pthread_create(&thread, NULL, f, argv[0]);
	if (res != 0) {
		perror("");
		exit(EXIT_FAILURE);
	}
	pthread_mutex_lock(&work_mutex);
	return 0;
}


void *f(void *f) {
}
