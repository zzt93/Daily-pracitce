#include <iostream>
/*
template <class T>
class A;

template <class T>
void f();
*/
template <class T>
class A {
    friend void f (T a);
    /*
      frined void f <T> (T t);
      we can treat it as a template specification
      so compiler have to find the declaration before it
    */
};

template <class T>
void f(T a){
    std::cout << a;
}

int main()
{
    A<int> a;
    f(1);
    f(1.1);
    return 0;
}
