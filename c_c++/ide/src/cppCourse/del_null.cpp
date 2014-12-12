#include <iostream>

class A{
    char *name;
public:
    A(char *n):name(NULL){
    }
    virtual ~A(){
        std::cout<< "in class A's destrcutor" << std::endl;
        delete[] name;
    }
};

class B: public A{
    int *p;
public:
    B():A(" "),p(NULL){
    }
    virtual ~B(){
        delete[] p;
    }
};
int main(int argc, char *argv[])
{
    B b;
    return 0;
}
