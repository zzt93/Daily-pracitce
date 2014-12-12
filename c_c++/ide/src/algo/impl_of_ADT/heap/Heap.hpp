#include <iostream>
#include "binary_tree.hpp"

template <class T>
class Hnode: public Tree_node<T>{
public:
    Hnode(T t):Tree_node<T>(t){}
}
    
template <class T>
class Heap{

    Hnode* root;
    
public:
    Heap():root(NULL){
    }
    Heap(T[]);
    Heap(const Heap&);
    virtual ~Heap();

    T& top();
    T pop();
    void add(T);

    void percolate_down();
    void percolate_up();
};