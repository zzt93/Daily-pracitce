#include <stdio.h>

/**
   Assumptions
   Integers are represented in two’s-complement form.
   Right shifts of signed data are performed arithmetically.
   Data type int is w bits long. For some of the problems, you will be given a
   specific value for w, but otherwise your code should work as long as w is a
   multiple of 8. You can use the expression sizeof(int)<<3 to compute w.

   Forbidden
   Conditionals (if or ?:), loops, switch statements, function calls, and macro
   invocations.
   Division, modulus, and multiplication.
   Relative comparison operators (<, >, <=, and >=).
   Casting, either explicit or implicit.

   Allowed operations
   All bit-level and logic operations.
   Left and right shifts, but only with shift amounts between 0 and w − 1.
   Addition and subtraction.
   Equality (==) and inequality (!=) tests. (Some of the problems do not allow
   these.)
   Integer constants INT_MIN and INT_MAX.
 */

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

#define packed_t unsigned
int xbyte(packed_t word, int bytenum) {
    signed char b = (word >> (bytenum << 3)) & 0xFF;
    return b;
}
int wrong_xbyte(packed_t word, int bytenum) {
    return (word >> (bytenum << 3)) & 0xFF;
}

/* 2.73 Addition that saturates to TMin or TMax
   Your function should follow the bit-level integer coding rules
 */
int saturating_add(int x, int y){
    int sum = x + y;
    int bits = sizeof x << 3;
    int xmsb = x >> (bits - 1)
        , ymsb = y >> (bits - 1)
        , smsb = sum >> (bits - 1);
    int p_o = !xmsb & !ymsb & smsb;
    int n_o = xmsb & ymsb & !smsb;
    int no_o = !p_o & !n_o;
    return (p_o & TMAX) | (n_o & TMIN) | (no_o & sum);
}

/* 2.74 Determine whether arguments can be subtracted without overflow */
int tsub_ok(int x, int y) {
    int res = x - y;
        int bits = sizeof x << 3;
    int xmsb = x >> (bits - 1)
        , ymsb = y >> (bits - 1)
        , rmsb = res >> (bits - 1);
    return !xmsb & ymsb & rmsb | xmsb & !ymsb & !rmsb;
}

int main() {
    int i;
    for (i = 0; i < 4; i++) {
        printf("%d ", xbyte(-1, i));
        printf("%d ", wrong_xbyte(-1, i));
    }
}
