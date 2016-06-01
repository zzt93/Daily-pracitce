#ifndef __PRIME_H__
#define __PRIME_H__

#define NOT_PRIME 1
#define MAY_BE_PRIME 0

#define START 2

typedef struct {
    int *has;
    int start;
    int num_cpus;
} arg_type;

static
void output(int *has, int n) {
    int k;
    for (k = 2; k < n; k++) {
        if (has[k] == MAY_BE_PRIME) {
            printf("%d ", k);
        }
    }
    puts("");
}

#endif /* __PRIME_H__ */