#include <iostream>


template <class T>
class Tree_node{
    T mdata;
    Tree_node<T> *mleft;//whether to add type parameters TODO
    Tree_node<T> *mright;
    
public:
    Tree_node(T t, Tree_node<T> *l = NULL, Tree_node<T> * r = NULL): mdata(t), mleft(l), mright(r){
    }
    virtual ~Tree_node(){
        if (mleft != NULL) {
            delete mleft;
        } else if (mright != NULL){
            delete mright;
        }
    }

    Tree_node(const Tree_node& n): mdata(n.data()), mleft(NULL), mright (NULL){
    }

    Tree_node<T> *left(){
        return mleft;
    }
    Tree_node<T> *right(){
        return mright;
    }
    void set_left(Tree_node<T> *l){
        mleft = l;
    }
    void set_right(Tree_node<T> *r){
        mright = r;
    }
    void set_data(T t){
        mdata = t;
    }
    T data(){
        return mdata;
    }
};


