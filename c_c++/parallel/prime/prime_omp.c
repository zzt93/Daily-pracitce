#include <stdio.h>
#include <omp.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <math.h>
#include "prime.h"

int size;
long long n;
int num_cpus;

static inline
int num_to_index(int num) {
    //assert((num & 1) == 1);
    return (num - 3) / 2;
}

static inline
int index_to_num(int i) {
    return i * 2 + 3;
}

static
void special_output(int *odd, int n) {
    int k;
    printf("2 ");
    for (k = 0; k < n; k++) {
        if (odd[k] == MAY_BE_PRIME) {
            printf("%d ", index_to_num(k));
        }
    }
    puts("");

}


static
void find_prime_for(void *arg) {
    int *odd = (int *)arg;
    //int has_i = num_to_index(i);
#pragma omp parallel for schedule(static, 1)
    for (int i = START_OPT; i < size; i+=2) {
        if (odd[num_to_index(i)] == MAY_BE_PRIME) {
            for (int j = i * i + i * 2; j < n; j = j + i * 2) {
                //printf("d%d ", j);
                odd[num_to_index(j)] = NOT_PRIME;
            }
        }
    }
}

void prime_opt_for(long long num) {
    n = num;
    size = sqrt(n) + 1;
    // store only odd number >= 3
    int count = (n - 2) / 2 + (n & 1);

    size_t mem = sizeof(int)*count;
    int *odd = malloc(mem);
    memset(odd, MAY_BE_PRIME, mem);

    find_prime_for(odd);
    //special_output(odd, count);
    free(odd);
}

// int main(int argc, char *argv[]){
//     if (argc <= 1) {
//         puts("Usage: prime N(upper bounds)");
//         return 0;
//     }
//     prime_opt(atoll(argv[1]));
//     return 0;
// }
