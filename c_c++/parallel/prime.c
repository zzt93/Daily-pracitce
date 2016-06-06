#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "prime.h"


void prime(long long n) {
    // too large n cause stack overflow
    size_t mem = sizeof(int)*(n + 1);
    int *odd = malloc(mem);
    memset(odd, MAY_BE_PRIME, mem);

    int size = sqrt(n) + 1;
    int i, j;
    for (i = 2; i < size; i++) {
        if (odd[i] == MAY_BE_PRIME) {
            for (j = i * i; j < n; j+=i) {
                odd[j] = NOT_PRIME;
            }
        }
    }
    //output(odd, n);
    free(odd);
}

//int main(int argc, char *argv[]) {
//    if (argc <= 1) {
//        puts("Usage: prime N(upper bounds)");
//        return 0;
//    }
//
//    long long n = atoll(argv[1]);
//    prime(n);
//    return 0;
//}