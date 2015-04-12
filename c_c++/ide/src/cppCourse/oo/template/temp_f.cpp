#include <iostream>

using std::cout;
using std::endl;

template <class T, class V>
void temp_function(T t, V v){
    t(v);
}

void f(int i){
    cout << i << endl;
}

template <class T>
void f1(T i){
    std::cout << i << std::endl;
}

int main(int argc, char *argv[])
{
    temp_function(f, 3);
    return 0;
}
