#include <stdio.h>

int itoa_s(int a, char *p, int limit) {
    int wid = 0;
    if (a < 0) {
        do {
            *--p = '0' - a % 10;
            wid++;
        } while ( (a /= 10) && wid < limit);
        *--p = '-';
    } else {
        do {
            *--p = '0' + a % 10;
            wid++;
        } while ((a /= 10) && wid < limit);
    }
    return wid;
}

char *itoa(int a) {
	static char buf[30];
	char *p = buf + sizeof(buf) - 1;
    int wid = itoa_s(a, p, sizeof buf);
    return p - wid;
}

int main()
{
    printf("%s", itoa(10));
    return 0;
}
