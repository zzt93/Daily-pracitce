#include <stdio.h>

int uadd_ok(unsigned int x, unsigned int y) {
    unsigned int sum = x + y;
    return x <= sum && y <= sum;
}

int tadd_ok(int x, int y) {
    int sum = x + y;
    int positive = 0;
    if (x > 0 && y > 0 && sum <= 0) {
        positive = 1;
    }
    int negative = 0;
    if (x < 0 && y < 0 && sum >= 0) {
        negative = 1;
    }
    return !positive && !negative;
}

int wrong_tsub_ok(int x, int y) {
    // fail when x < 0, and y == TMIN, for -y also equals to TMIN
    tadd_ok(x, -y);
}

/**
   It’s not realistic to test this function for all possible values of x and y. Even if you
   could run 10 billion tests per second, it would require over 58 years to test all
   combinations when data type int is 32 bits. On the other hand, it is feasible to test
   your code by writing the function with data type short or char and then testing
   it exhaustively.
 */
int tmul_ok(int x, int y) {
    int p = x * y;
    return !x || p/x == y;
}

int tmul_ok2(int x, int y) {
    // the cast is essential, or it may compute as 32bit and overflow, then
    // sign extent to 64bit
    long long t = ((long long) x) * y;
    return t == (int)t;
}

int div16(int x) {
    int bias = (x >> 31) & 0xf;
    return (x + bias) >> 4;
}

int main(int argc, char*argv[]) {
}