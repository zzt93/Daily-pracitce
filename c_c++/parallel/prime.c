#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "prime.h"


void prime(long long n) {
    // too large n cause stack overflow
    int has[n + 1];
    memset(has, MAY_BE_PRIME, sizeof has);
    int size = sqrt(n) + 1;
    int i, j;
    for (i = 2; i < size; i++) {
        if (has[i] == MAY_BE_PRIME) {
            for (j = i * i; j < n; j+=i) {
                has[j] = NOT_PRIME;
            }
        }
    }
    //output(has, n);
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