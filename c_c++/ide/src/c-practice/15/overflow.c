#include <stdio.h>
#include <stdint.h>

int main()
{
    uint8_t a = 1;
    uint8_t b = 0x7f;
    uint8_t c = a + b;
    unsigned int aa = a;
    unsigned int bb = b;
    unsigned int cc = c;
    printf("%u %u %u\n", aa, bb, cc);
    uint8_t d = 0xff;
    uint8_t e = 0x80;
    uint8_t f = d + e;
    unsigned int da = d;
    unsigned int eb = e;
    unsigned int fc = f;
    printf("%u %u %u\n", da, eb, fc);
    uint8_t h = 0xfe;
    uint8_t i = 0x7f;
    uint8_t g = h + (~i + 1);
    unsigned int ha = h;
    unsigned int ib = i;
    unsigned int gc = g;
    printf("%u %u %u\n", ha, ib, gc);


    return 0;
}
