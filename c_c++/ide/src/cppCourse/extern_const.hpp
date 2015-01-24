#include <iostream>
extern const int n;
extern const int a[];
/* error: too many initializers for ‘const int [1]’
 extern const int a[] = {1, 2};*/
void increse();
