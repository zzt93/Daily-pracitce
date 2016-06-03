#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <math.h>
#include "prime.h"

int size;
long long n;

static inline
int num_to_index(int num) {
    //assert((num & 1) == 1);
    return (num - 3) / 2;
}

static inline
int index_to_num(int i) {
    return i * 2 + 3;
}

void special_output(int *has, int n) {
    int k;
    printf("2 ");
    for (k = 0; k < n; k++) {
        if (has[k] == MAY_BE_PRIME) {
            printf("%d ", index_to_num(k));
        }
    }
    puts("");

}

void *find_prime_unrolling(void *arg) {
        arg_type *tmp = (arg_type *)arg;
    int start = tmp->start;
    int gap = start - START;
    int num_cpus = tmp->num_cpus;
    int *has = tmp->has;
    int i, j;
    for (i = START_OPT; i < size; i+=2) {
        if (has[num_to_index(i)] == MAY_BE_PRIME) {
            for (j = i * i + gap * i * 2; j < n; j = j + i * num_cpus * 2) {
                //printf("d%d ", j);
                has[num_to_index(j)] = NOT_PRIME;
            }
        }
    }
    return NULL;
}

void *find_prime_opt(void *arg) {
        arg_type *tmp = (arg_type *)arg;
    int start = tmp->start;
    int gap = start - START;
    int num_cpus = tmp->num_cpus;
    int *has = tmp->has;
    int i, j;
    for (i = START_OPT; i < size; i+=2) {
        if (has[num_to_index(i)] == MAY_BE_PRIME) {
            for (j = i * i + gap * i * 2; j < n; j = j + i * num_cpus * 2) {
                //printf("d%d ", j);
                has[num_to_index(j)] = NOT_PRIME;
            }
        }
    }
    return NULL;
}

void prime_opt(long long num) {
    n = num;
    size = sqrt(n) + 1;
    // store only odd number >= 3
    int count = (n - 2) / 2 + (n & 1);

    int res;
    size_t mem = sizeof(int)*count;
    int *odd = malloc(mem);
    memset(odd, MAY_BE_PRIME, mem);

    int num_cpus = sysconf(_SC_NPROCESSORS_ONLN);
    int i;
    pthread_t a_thread[num_cpus];
    arg_type *arg = calloc(num_cpus, sizeof(arg_type));

    Thread_Funcition thread_f = find_prime_opt;

    for (i = 1; i < num_cpus; i++) {
        arg[i].has = odd;
        arg[i].num_cpus = num_cpus;
        arg[i].start = i + START;
        // argument will be sent to thread directly,
        // so use malloc or static
        res = pthread_create(&a_thread[i], NULL, thread_f, arg+i);
        if (res != 0) {
            perror("");
            exit(EXIT_FAILURE);
        }
    }
    i = 0;
    arg[i].has = odd;
    arg[i].num_cpus = num_cpus;
    arg[i].start = i + START;
    thread_f(arg+i);

    for (i = 1; i < num_cpus; i++) {
        pthread_join(a_thread[i], NULL);
    }
    //special_output(odd, count);
}

// int main(int argc, char *argv[]){
//     if (argc <= 1) {
//         puts("Usage: prime N(upper bounds)");
//         return 0;
//     }
//     prime_opt(atoll(argv[1]));
//     return 0;
// }