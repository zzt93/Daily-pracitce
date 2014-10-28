#include <iostream>

template <class T>
class Tree{
public :
    T data;
    Tree(T&);
};

template <class T>
Tree<T>::Tree(T& d){
    data = d;
}

int main(int argc, char *argv[])
{
    int x = 5;
    Tree<int> tr(x);
    x--;
    std::cout << tr.data;
    return 0;
}
