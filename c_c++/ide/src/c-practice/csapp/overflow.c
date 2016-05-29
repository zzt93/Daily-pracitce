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

int tmul_ok(int x, int y) {
    int p = x * y;
    return !x || p/x == y;
}

int main(int argc, char*argv[]) {
}