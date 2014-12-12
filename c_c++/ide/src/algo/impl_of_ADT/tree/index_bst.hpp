#include <iostream>
#include <vector>
#include <stack>
#include "binary_tree.hpp"

template <class T>
class Index_tree;

template <class T>
class Index_tree_node: public Tree_node<T>{//inherit with type params
    int left_size;

    void set_data(T t){
        Tree_node<T>::set_data(t);
    }
    void set_left(Index_tree_node<T> *l){
        Tree_node<T>::set_left(l);
    }
    void set_right(Index_tree_node<T> *r){
        Tree_node<T>::set_right(r);
    }

public:
    Index_tree_node(T t, Index_tree_node<T> *l = NULL, Index_tree_node<T> * r = NULL):
        Tree_node<T>(t, l, r), left_size( (l == NULL)?0:l->ordinal() ){//invoke the constructor with type params
    }

    Index_tree_node(const Index_tree_node<T>& node):
        Tree_node<T>(node),
        left_size(node.ordinal()-1 ){
    }

    friend Index_tree<T>;
    //friend class Index_tree;

    int ordinal(){
        return left_size+1;
    }
    T data(){
        return Tree_node<T>::data();//have to add Tree_node<T>::to make compiler find the function
    }
    Index_tree_node<T>* left(){
        return (Index_tree_node<T>*)Tree_node<T>::left();
    }
    Index_tree_node<T>* right(){
        return (Index_tree_node<T>*)Tree_node<T>::right();
    }
};

template <class T>
class Index_tree {

    Index_tree_node<T> *m_root;

    
    Index_tree_node<T> *find(T t);
    void delete_tree(Index_tree_node<T> *root);
    Index_tree_node<T> *make_tree(Index_tree_node<T> *root);
    void set_root(Index_tree_node<T> *root){
        m_root = root;
    }
    
public:
    Index_tree(): m_root(NULL){}
    Index_tree(T a): m_root(Index_tree_node<T>(a)){}
    Index_tree(T*, int);
    
    virtual ~Index_tree(){
        Index_tree<T>::delete_tree(m_root);
    }
    
    Index_tree(const Index_tree<T>& t){
        Index_tree<T>::set_root(Index_tree<T>::make_tree(t.root()));
    }

    Index_tree_node<T>* root(){
        return m_root;
    }

    Index_tree<T>& operator = (const Index_tree<T>&);
    friend std::ostream& operator << (std::ostream& os, Index_tree<T>& tree){
        std::stack<Index_tree_node<T>*> node_stack;
        if (tree.empty()){
            return os;
        }
        node_stack.push(tree.root());
        while (!node_stack.empty()) {
            Index_tree_node<T> *temp = node_stack.top();
            os << temp->data() << std::endl;
            if(temp->left()!=NULL){
                node_stack.push(temp->left());
            }
            if(temp->right()!=NULL){
                node_stack.push(temp->right());
            }
            node_stack.pop();
        }
        return os;
    }

    bool deleteNode(T t);
    bool has(T t);
    bool empty(){
        return m_root==NULL;
    }
};


/*
  make a complete binary tree
*/
template <class T>
Index_tree<T>::Index_tree(T* t, int size){
    std::vector<Index_tree_node<T>* > nodes;
    for (int i = 0; i < size; ++i) {
        Index_tree_node<T> *temp = new Index_tree_node<T>(t[i]);
        nodes.push_back(temp);
    }
    for (int i = 0; 2*i+2 < size; ++i) {
        nodes[i]->set_left(nodes[2*i+1]);
        nodes[i]->set_right(nodes[2*i+2]);
    }
}

template <class T>
Index_tree_node<T> *Index_tree<T>::make_tree(Index_tree_node<T> *root){
    if (root == NULL) {
        return NULL;
    } else {
        std::cout << root->data() << std::endl;
        Index_tree_node<T> *l = Index_tree<T>::make_tree(root->left());
        Index_tree_node<T> *r = Index_tree<T>::make_tree(root->right());
        Index_tree_node<T> *temp = new Index_tree_node<T>(root->data(), l, r);
        return temp;
    }
}

template <class T>
void Index_tree<T>::delete_tree(Index_tree_node<T> *root){
    if (root == NULL) {
        return;
    } else {
        std::cout << root->data() << std::endl;
        Index_tree<T>::delete_tree(root->left());
        Index_tree<T>::delete_tree(root->right());
        delete root;
    }
}

template <class T>
bool Index_tree<T>::has(T t){
    return false;
}

template <class T>
bool Index_tree<T>::deleteNode(T t){
    if (!has(t)) {
        return false;
    } else {
        Index_tree_node<T> * temp = find(t);
        if(temp->left == NULL &&
           temp->right == NULL){
            //the node is leaf
        } else if(temp->left == NULL ||
                  temp->right == NULL){
            //the node has one children
        } else {
            //the node has two children
        }
    }
}

/*
  params: generic type
  return value: the pointer to t's father
*/
template <class T>
Index_tree_node<T> * Index_tree<T>::find(T t){
}
