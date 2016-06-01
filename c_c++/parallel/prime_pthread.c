#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <math.h>
#include "prime.h"

int size;
long long n;

// following two version has no synchronization, which
// not affect the correctness, but will make some duplicate check
// whether the lock will increase the throughput need to compare
// with find_prime_locked();
void *find_prime(void *arg) {
    arg_type *tmp = (arg_type *)arg;
    int start = tmp->start;
    int num_cpus = tmp->num_cpus;
    int *has = tmp->has;
    int i, j;
    //printf("s%d ", start);
    for (i = start; i < size; i+=num_cpus) {
        if (has[i] == MAY_BE_PRIME) {
            for (j = i * i; j < n; j+=i) {
                //printf("d%d ", j);
                has[j] = NOT_PRIME;
            }
        }
    }
    pthread_exit(NULL);
}

void *find_prime2(void *arg) {
        arg_type *tmp = (arg_type *)arg;
    int start = tmp->start;
    int gap = start - START;
    int num_cpus = tmp->num_cpus;
    int *has = tmp->has;
    int i, j;
    for (i = START; i < size; i++) {
        if (has[i] == MAY_BE_PRIME) {
            for (j = i * i + gap * i; j < n; j = j + i * num_cpus) {
                //printf("d%d ", j);
                has[j] = NOT_PRIME;
            }
        }
    }
    pthread_exit(NULL);
}

pthread_mutex_t work_mutex =
    PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond =
    PTHREAD_COND_INITIALIZER;
long long reach_count = 0;
void *find_prime_locked(void *arg) {
    arg_type *tmp = (arg_type *)arg;
    int gap = tmp->start - START;
    int num_cpus = tmp->num_cpus;
    int *has = tmp->has;
    int i, j;
    for (i = START; i < size; i++) {
        if (has[i] == MAY_BE_PRIME) {
            for (j = i * i + gap * i; j < n; j = j + i * num_cpus) {
                //printf("d%d ", j);
                has[j] = NOT_PRIME;
            }
        }
        /**
           Failed trial to simulate cyclic barrier:
           if the fourth thread run immediately to
           reach here again, other thread will not wake,
           cause deadlock
        status = pthread_mutex_lock(&work_mutex);
        reach_count++;
        if (reach_count != num_cpus) {
            while (reach_count != num_cpus || reach_count != 0) {
                pthread_cond_wait(&cond, &work_mutex);
            }
        } else {
            pthread_cond_broadcast(&cond);
            reach_count = 0;
        }
        pthread_mutex_unlock(&work_mutex);
         */
        pthread_mutex_lock(&work_mutex);
        reach_count++;
        if (reach_count != num_cpus) {
            if (reach_count != num_cpus || reach_count != 0) {
                pthread_cond_wait(&cond, &work_mutex);
            }
        } else {
            pthread_cond_broadcast(&cond);
            reach_count = 0;
        }
        pthread_mutex_unlock(&work_mutex);
    }
    pthread_exit(NULL);
}

void prime_pthread() {
	int res;
    int has[n + 1];
    memset(has, MAY_BE_PRIME, sizeof has);

    int num_cpus = sysconf(_SC_NPROCESSORS_ONLN);
    int i;
	pthread_t a_thread[num_cpus];
    arg_type *arg = calloc(num_cpus, sizeof(arg_type));

    for (i = 0; i < num_cpus; i++) {
        arg[i].has = has;
        arg[i].num_cpus = num_cpus;
        arg[i].start = i + START;
        // argument will be sent to thread directly,
        // so use malloc or static
        res = pthread_create(&a_thread[i], NULL, find_prime, arg+i);
        if (res != 0) {
            perror("");
            exit(EXIT_FAILURE);
        }
    }
    for (i = 0; i < num_cpus; i++) {
        pthread_join(a_thread[i], NULL);
    }
    //output(has, n);
}

void set_argu_prime(long long num) {
    n = num;
    size = sqrt(n) + 1;
    prime_pthread();
}

//int main(int argc, char *argv[]){
//    if (argc <= 1) {
//        puts("Usage: prime N(upper bounds)");
//        return 0;
//    }
//    set_argu_prime(atoll(argv[1]));
//  	return 0;
//}
