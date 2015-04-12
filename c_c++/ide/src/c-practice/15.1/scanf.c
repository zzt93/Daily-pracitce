#include <stdio.h>

int main(int argc, char *argv[])
{
    char s[10];
    char s1[10];
    scanf("%s ", s);
    scanf("%s", s1);
    printf("%s\n%s\n", s, s1);
    
    //scanf("%s*%s", s, s1);
    //printf("%s\n%s\n", s, s1);
    return 0;
}
