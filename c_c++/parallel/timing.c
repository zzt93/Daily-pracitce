#include <stdio.h>
#include <time.h>
#include <unistd.h>

void prime(long long);
void set_argu_prime(long long n);
int main(int argc, char *argv[]) {
    int res;
    long long n;
    int times = 10;
    int i;
    // warm up
    prime(1000);
    set_argu_prime(1000);
    while((res = scanf("%lld", &n)) != EOF) {
        printf("simple : multiple -- %d\n", n);
        clock_t start = clock();
        for (i = 0; i < times; i++) {
            prime(n);
        }
        printf("%u ", clock() - start);
        sleep(1);
        start = clock();
        for (i = 0; i < times; i++) {
            set_argu_prime(n);
        }
        printf("%u ", clock() - start);
        puts("");
    }
}