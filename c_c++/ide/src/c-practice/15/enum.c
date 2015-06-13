#include <stdio.h>

enum {
    D, UP, E
} d ;

int main()
{
    d = UP;
    if (d == 1) {
        printf("equal");
    }
    return 0;
}
