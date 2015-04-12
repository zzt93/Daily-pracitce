#include <stdio.h>
#include <stdint.h>

#define MAX16 24

char * color[] = {"\x1b[31m",
                  "\x1b[32m",
                  "\x1b[32m",
                  "\x1b[33m",
                  "\x1b[34m",
                  "\x1b[35m",
                  "\x1b[36m",
                  "\x1b[0m",
};

#define COLOR_SIZE (sizeof(color)/sizeof(color[0]))

/*
#define ANSI_COLOR_RED     "\x1b[31m"
#define ANSI_COLOR_GREEN   "\x1b[32m"
#define ANSI_COLOR_YELLOW  "\x1b[33m"
#define ANSI_COLOR_BLUE    "\x1b[34m"
#define ANSI_COLOR_MAGENTA "\x1b[35m"
#define ANSI_COLOR_CYAN    "\x1b[36m"
#define ANSI_COLOR_RESET   "\x1b[0m"
*/

//FIXME not right implementation when to > 26
uint16_t lcarry = 0;
uint16_t rcarry = 0;
//uint16_t carry = 0;
uint16_t map[MAX16] = {0, 1};

uint16_t fib(uint16_t to);

uint16_t add_map(uint16_t to) {
    if (to <= MAX16 && map[to] == 0) {
        map[to] = fib(to);
        //printf("%s 0x%x%x %s", color[to%COLOR_SIZE], rcarry, map[to], color[COLOR_SIZE-1]);
    }
    if (to > MAX16) {
        return fib(to);
    }
    return map[to];
}

uint16_t fib(uint16_t to) {
    if (to == 0) {
        return map[0];
    } else if(to == 1) {
        return map[1];
    }
    uint16_t f = add_map(to - 1);
    uint16_t s = add_map(to - 2);
    uint16_t tmp = f + s;
    //high 16 bit calculation
    uint16_t tmp_rcarry = rcarry;
    rcarry += lcarry;
    //consider the carry of low 16 bit
    if (f > tmp || s > tmp) {
        rcarry += 1;
    }
    lcarry = tmp_rcarry;
    return tmp;
}

void f(uint16_t to, uint16_t* res) {
    *res = add_map(to);
}

int main()
{
    printf("OS lab1, fib\nPlease Enter an aim\n");
    uint16_t to = 0;
    unsigned int i = 0;
    while (scanf("%hd", &to) != EOF) {
        
        uint16_t res;
        i = 0;
        for (; i <= to; ++i) {
            rcarry = 0;
            lcarry = 0;
            f(i, &res);
            if (to <= MAX16) {
                printf("%s %d %s", color[i%COLOR_SIZE], res, color[COLOR_SIZE-1]);
            } else {
                //printf("carry is %d", carry);
                printf("%s 0x%x%x %s", color[i%COLOR_SIZE], rcarry, res, color[COLOR_SIZE-1]);
            }
        }
        puts("");
    }
    return 0;
}
