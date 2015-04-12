#include <stdio.h>
#include <stdlib.h>
struct a{
    int i;
    struct{
        char b[8];
        int a;
    };
} r[2];

int main(int argc, char *argv[]){
    /*     r[0] = {
        NULL,
        1};
     r[1] = {"a", 2};//can't assign array like this
     
       test_atoi.c:12:13: error: expected expression before ‘{’ token
      r[1] = {"a", 2};
     
     printf("%d%s", r[0].a, r[1].b);
    */
    r[0].a = 1;
    r[1].i = 2;
    printf("%d%d", r[0].a, r[1].i);

    return 1;
}