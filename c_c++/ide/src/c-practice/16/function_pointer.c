#include <stdio.h>

/**
   The typedef-names are aliases for existing types, and are not declarations of new types. Typedef cannot be used to change the meaning of an existing type name (including a typedef-name). Once declared, a typedef-name may only be redeclared to refer to the same type again.
   typedef declaration does not introduce a distinct type, it only establishes a synonym for an existing type, thus typedef names are compatible with the types they alias. Typedef names share the name space with ordinary identifiers such as enumerators, variables and function.

 */
//typedef (int *)(*F)();
// declare a function type
typedef int * F();

typedef int * G();

typedef G H;
// declare a function pointer type
typedef int * (*PF)();


/*
(int *) fu(int a) {
    return NULL;
}
*/

int main() {
    F f;
    F *fp;
    PF pf;
    G g;
    H h;
}