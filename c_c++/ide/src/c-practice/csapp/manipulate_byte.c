#include <stdio.h>


unsigned replace_byte(unsigned x, int i, unsigned char b) {
    int bytes = sizeof x;
    if (i >= bytes) {
        i %= bytes;
    }
    return (x & ~(0xff << i)) | (b << i);
}

int int_shifts_are_arithmetic() {
    return (-1 >> 1) == -1;
}

unsigned srl(unsigned x, int k) {
    /* Perform shift arithmetically */
    unsigned xsra = (int) x >> k;
    return ((unsigned)-1 >> k) & xsra;
}

int sra(int x, int k) {
    /* Perform shift logically */
    int xsrl = (unsigned) x >> k;
    int bits = (sizeof x) * 8;
    int msb = (unsigned) x >> (bits - 1);
    return xsrl & (msb << (bits - k));
}

/* Return 1 when x contains an odd number of 1s; 0 otherwise.
Assume w=32. */
int odd_ones(unsigned x) {
    x = x ^ (x >> 16);
    x = x ^ (x >> 8);
    x = x ^ (x >> 4);
    x = x ^ (x >> 2);
    x = x ^ (x >> 1);
    return x;
}

/*
* Generate mask indicating leftmost 1 in x. Assume w=32.
* For example 0xFF00 -> 0x8000, and 0x6600 --> 0x4000.
* If x = 0, then return 0.
*/
int leftmost_one(unsigned x) {
    x |= (x >> 1);
    x |= (x >> 2);
    x |= (x >> 4);
    x |= (x >> 8);
    x |= (x >> 16);
    return x ^ (x >> 1);
}


int main() {
}