#include <omp.h>
#include <stdio.h>

void work() {
    int tmp = 10;
    static int count = 0;
    count++;
    printf("%d, %d\n", count, tmp);
}

int main() {
    double a[10];
#pragma omp parallel
    work();
}
