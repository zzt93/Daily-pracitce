#include <iostream>
#include <windows.h>
#include <process.h>
#include "prime.h"

int size;
long long n;

void set_not_prime(int *p) {
    while (InterlockedExchange(p, NOT_PRIME) == MAY_BE_PRIME) {
    }
}

unsigned int WINAPI ThreadFunc(void* pvParam){
    arg_type *tmp = (arg_type *)pvParam;
    int gap = tmp->start - START;
    int num_cpus = tmp->num_cpus;
    int *has = tmp->has;
    int i, j;
    for (i = START; i < size; i++) {
        if (has[i] == MAY_BE_PRIME) {
            for (j = i * i + gap * i; j < n; j+=(i*num_cpus)) {
                set_not_prime(has + j, NOT_PRIME);
            }
        }
    }
    _endthreadex(2);
    return 1;
}

void windows_prime(long long num) {
    n = num;
    size = sqrt(n) + 1;
    int has[n + 1];
    memset(has, MAY_BE_PRIME, sizeof has);

    unsigned int threadId;
    int num_cpus = ;
    arg_type *arg = calloc(num_cpus, sizeof(arg_type));
    int ii = 0;
    for(ii = 0; ii < num_cpus ; ++ii){
        arg[ii].has = has;
        arg[ii].num_cpus = num_cpus;
        arg[ii].start = ii + START;

        HANDLE thread = (HANDLE)_beginthreadex(NULL, 0, ThreadFunc, arg+ii, 0, &threadId);
    }
}

int main(int argc, char *argv[]) {
    if (argc <= 1) {
        puts("Usage: prime N(upper bounds)");
        return 0;
    }

    long long n = atoll(argv[1]);
    windows_prime(n);
    output(has, n);
    return 0;
}