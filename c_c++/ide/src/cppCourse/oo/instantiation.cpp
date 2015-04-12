#include <iostream>
#include "instantiation.hpp"

using std::cout;
using std::endl;

template <class T>
T my_max(T t1, T t2){
    return (t1>t2)?t1:t2;
}

/*
int main(int argc, char *argv[])
{
    cout << max(11., 2.1);//1, 2.1 error:deduction failed
    return 0;
}
*/

// explicit instantiation
// template double my_max(double, double);

void use_max(){
    cout << my_max(1.1, 2.1);
}