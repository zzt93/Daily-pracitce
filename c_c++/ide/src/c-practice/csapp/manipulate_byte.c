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

/* 
Return 1 when x contains an odd number of 1s; 0 otherwise.
Assume w=32. 
GET: 
1. xor keep 1's number still odd/even
2. bit manipulate can work like following recusive way.
*/
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

/*
* Do rotating left shift. Assume 0 <= n < w
* Examples when x = 0x12345678 and w = 32:
* n=4 -> 0x23456781, n=20 -> 0x67812345
*/
unsigned rotate_left(unsigned x, int n) {
    int w = sizeof x * 8;
    return (x >> (w - n - 1) >> 1) | (x << n);
}

/*
* Return 1 when x can be represented as an n-bit, 2’s complement
* number; 0 otherwise
* Assume 1 <= n <= w
*/
int fits_bits(int x, int n) {
    int res = (x >> (n - 1));
    // res should be -1 or 0
    return !(~res) || !res;
}

int main() {
}
