#include <stdio.h>
#include <time.h>
#include <sys/time.h>
#include <unistd.h>

suseconds_t getms(struct timeval *s, struct timeval *e) {
    return (e->tv_sec - s->tv_sec) * 1000000 + e->tv_usec - s->tv_usec;
}

void prime(long long);
void set_argu_prime(long long n);
void prime_opt(long long num);

int main(int argc, char *argv[]) {
    int res;
    long long n;
    int times = 10;
    int i;
    // warm up
    prime(1000);
    set_argu_prime(1000);
    prime_opt(1000);
    printf("simple : multiple : opt -- ");
    while((res = scanf("%lld", &n)) != EOF) {
        //clock_t start = clock();
        struct timeval start, end;
        gettimeofday(&start, NULL);
        for (i = 0; i < times; i++) {
            prime(n);
        }
        gettimeofday(&end, NULL);
        printf("%ld ", getms(&start, &end));

        sleep(1);

        //start = clock();
        gettimeofday(&start, NULL);
        for (i = 0; i < times; i++) {
            set_argu_prime(n);
        }
        gettimeofday(&end, NULL);
        printf("\t%ld ", getms(&start, &end));

        sleep(1);

        gettimeofday(&start, NULL);
        for (i = 0; i < times; i++) {
            prime_opt(n);
        }
        gettimeofday(&end, NULL);
        printf("\t%ld ", getms(&start, &end));

        printf("\nsimple : multiple : opt -- ");
    }
}
