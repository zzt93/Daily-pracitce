#include <omp.h>


/**
   they are independent compiling unit, which
   compiler will not take `tmp` as private
   -----other.c----------
   extern int tmp;
   void work() {
     tmp = 5;
   }
 */
void work();

int tmp;
void wrong() {
    tmp = 0;
#pragma omp parallel private(tmp)
    {
        /*
          initial value and result is unspecified by 2.5
         */
        tmp++;
        work();
    }
}


int main() {
    double a[10];
#pragma omp parallel
    work();
}
