#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

static pthread_key_t thread_log_key;
void write_to_thread_log(char const *msg) {
	FILE *thread_log = (FILE *)pthread_getspecific(thread_log_key);
	fprintf(thread_log, "%s\n", msg);
}

// invoke when thread end
void close_thread_log(void *thread_log) {
	fclose((FILE *)thread_log);
}

void *thread_f(void *f) {
	char thread_log_filename[20];
	FILE *thread_log;
	sprintf(thread_log_filename, "thread%d,log", (int)pthread_self());
	thread_log = fopen(thread_log_filename, "w");
	pthread_setspecific(thread_log_key, thread_log);
	write_to_thread_log("Thread starting.");
	return NULL;
}

int main(int argc, char *argv[]){
	int i;
	pthread_t thread[5];
	pthread_key_create(&thread_log_key, close_thread_log);
	for(i = 0; i < 5; i++) {
		pthread_create(&pthread[i], NULL, thread_f, NULL);
	}
	for(i = 0; i < 5; i++) {
		pthread_join(&thread[i], NULL);
	}
	pthread_key_delete(thread_log_key);
	return 0;
}
