#include <iostream>
#include <vector>

class A {
    int i;
    static const int const_i = 1;
public:
    A(){
        std::cout << "in A's constructor" << std::endl;
    }
    ~A(){
        std::cout << "in A's destructor" << std::endl;
    }
    A(const A& a){// work only with -fno-elide-constructors
        std::cout << "in A's copy" << std::endl;
    }
    A& operator = (A& a){
        std::cout << "in A's assignment operator" << std::endl;
    }
    // test whether const is just useful when invoke
    void print() const {
        //++i;
        //const_i++;
        std::cout << "-----------------------" << std::endl;
    }
};

class B {
    std::vector<int> nums;
    std::vector<int>::iterator it;
public:
    B(){
        it = nums.begin();
        std::cout << "in B's constructor" << std::endl;
    }
    ~B(){
        std::cout << "in B's destructor" << std::endl;
    }
    /*
      the following three functions is for copy_iterator.cpp */
    void restart() {
        it = nums.begin();
    }
    void add (int i){
        nums.push_back(i);
    }
    int next(){
        return *it++;
    }
};

class C : public B{
public:
    C(){
        std::cout << "in C's constructor" << std::endl;
    }
    ~C(){
        std::cout << "in C's destructor" << std::endl;
    }
private:
    A a;
};
