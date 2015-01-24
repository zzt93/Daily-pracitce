#include<stdio.h>

void init(char a[]){
    int i;
    for (i = 0;i < 5 && a[i] != 0; ++i) {
        printf("%d\n", a[i]);
    }
}
int main(int argc, char *arg[]){
    char a[4] = {4};
    init(a);
    return 0;
}