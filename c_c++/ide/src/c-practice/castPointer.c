#include <stdio.h>
typedef struct{
	int i;
	char c;
} Test;
int main(){
   int i = -1;
   int *p = &i;
   printf("%f\n", *(float*)p);
   printf("%lu\n", sizeof (Test));
   return 0;
}
