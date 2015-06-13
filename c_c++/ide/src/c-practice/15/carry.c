#include <stdio.h>

int main(int argc, char *argv[])
{
    
    /*if left < right when computing left−right, and a borrow must be performed
     */
    int a = 0x7fffffff;
    int b = 2;
    // test carry bit
    //int c = 1 - b;// -- carry (borrow)
    // test overflow bit
    //int d =  a * b; // -- overflow
    // unsigned addition
    unsigned int e = 0x1;
    unsigned int f = a;
    //unsigned int g = f + e; // -- overflow
    // signed subtraction
    int h = -a;
    int j = h + -10;// -- no overflow
    // signed addition
    int l = a + e;
    return 0;
}
