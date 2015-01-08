#include "test_class.hpp"
#include <iostream>

using std::cout;
using std::endl;

A test_copy_times(){
    A a;
    cout << "copy times" << endl;
    return a;
}
void copy(A a){
    cout << "copy" << endl;
    a.print();
}
int main(int argc, char *argv[])
{
    A a = test_copy_times();
    a.print();
    copy(a);
    return 0;
}
