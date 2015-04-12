#include <iostream>
#include <vector>
#include <queue>
#include "binary_tree.hpp"

template <class T>
class Index_tree;

template <class T>
class Index_tree_node: public Tree_node<T>{//inherit with type params
    int left_size;

    void set_size(int i){
        left_size = i;
    }
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

    int ordinal() const{
        return left_size+1;
    }
    T data() const{
        return Tree_node<T>::data();//have to add Tree_node<T>::to make compiler find the function
    }
    Index_tree_node<T>* left() const{
        return (Index_tree_node<T>*)Tree_node<T>::left();
    }
    Index_tree_node<T>* right() const{
        return (Index_tree_node<T>*)Tree_node<T>::right();
    }
};



/*
  This tree is convenient for find the ordinal of a element.
  left node <= fa < right node
*/
template <class T>
class Index_tree {

    Index_tree_node<T> *m_root;

    Index_tree_node<T> *find(T t);//find the node contains the element
    Index_tree_node<T> *find_position(T);//find the father of node contains the element
    void delete_tree(Index_tree_node<T> *root);
    Index_tree_node<T> *copy_tree(const Index_tree_node<T> *root);
    void set_root(Index_tree_node<T> *root){
        m_root = root;
    }
    int count_tree(Index_tree_node<T>*);
public:
    Index_tree(): m_root(NULL){}
    Index_tree(T a): m_root(Index_tree_node<T>(a)){}
    Index_tree(T*, int);
    
    virtual ~Index_tree(){
        Index_tree<T>::delete_tree(m_root);
    }
    
    Index_tree(const Index_tree<T>& t){
        Index_tree<T>::set_root(Index_tree<T>::copy_tree(t.root()));
    }

    Index_tree_node<T>* root() const {
        return m_root;
    }

    Index_tree<T>& operator = (const Index_tree<T>& t){
        if (this == &t){
            return *this;
        }
        Index_tree<T>::delete_tree(this->root());
        this->set_root(Index_tree<T>::copy_tree(t.root()));
        return *this;
    }
    friend std::ostream& operator << (std::ostream& os, Index_tree<T>& tree){
        std::queue<Index_tree_node<T>*> node_queue;
        if (tree.empty()){
            return os;
        }
        node_queue.push(tree.root());
        while (!node_queue.empty()) {
            Index_tree_node<T> *temp = node_queue.front();
            os << temp->data() << std::endl;
            if(temp->left()!=NULL){
                node_queue.push(temp->left());
            }
            if(temp->right()!=NULL){
                node_queue.push(temp->right());
            }
            node_queue.pop();
        }
        return os;
    }

    bool deleteNode(T t);
    bool has(T t) const;
    bool empty() const{
        return m_root==NULL;
    }
    bool add(T);
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
    for (int i = 0; 2*i+1 < size; ++i) {
        nodes[i]->set_left(nodes[2*i+1]);
        if (2*i+2 >= size) {
            break;
        }
        nodes[i]->set_right(nodes[2*i+2]);
    }
    m_root = nodes[0];
    //add the left size
    for (int i = 0; i < size; i++) {
        nodes[i]->set_size( count_tree(nodes[i]->left()) );
    }
    //TODO sort the tree
    
}

template <class T>
int Index_tree<T>::count_tree(Index_tree_node<T>* root){
    if (root == NULL){
        return 0;
    }
    //std::cout << root->data() << std::endl;
    return count_tree(root->left())+count_tree(root->right()) + 1;//the root
}

template <class T>
Index_tree_node<T> *Index_tree<T>::copy_tree(const Index_tree_node<T> *root){
    if (root == NULL) {
        return NULL;
    } else {
        Index_tree_node<T> *l = Index_tree<T>::copy_tree(root->left());
        Index_tree_node<T> *r = Index_tree<T>::copy_tree(root->right());
        //std::cout << root->data() << std::endl;
        Index_tree_node<T> *temp = new Index_tree_node<T>(root->data(), l, r);
        return temp;
    }
}

template <class T>
void Index_tree<T>::delete_tree(Index_tree_node<T> *root){
    if (root == NULL) {
        return;
    } else {
        Index_tree<T>::delete_tree(root->left());
        Index_tree<T>::delete_tree(root->right());
        //std::cout << root->data() << std::endl;
        delete root;
    }
}

template <class T>
bool Index_tree<T>::has(T t) const{
    if (find(t) != NULL){
        return true;
    }
    return false;
}

template <class T>
bool Index_tree<T>::deleteNode(T t){
    //TODO
    if (!has(t)) {
        return false;
    } else {
        Index_tree_node<T> * temp = find(t);
        int nums = count_child(temp);
        if(nums == 0){
            //the node is leaf
        } else if(nums == 1){
            //the node has one children
        } else {
            //the node has two children
        }
        return true;
    }
}

/*
  params: generic type
  return value: the pointer to the first node that has value of t
*/
template <class T>
Index_tree_node<T> * Index_tree<T>::find(T t){
    Index_tree_node<T> * root = root();
    while (root != NULL){
        if (root->data() > t) {
            root = root->left();
        } else if (root->data() < t){
            root = root->right();
        } else {
            return root;
        }
    }
    if (root == NULL){
        return NULL;
    }
}

template <class T>
Index_tree_node<T> * Index_tree<T>::find_position(T t){
    if (root() == NULL){
        return NULL;
    }
    Index_tree_node<T> * fa = root();
    Index_tree_node<T> * root = root();
    while (root != NULL){
        if (root->data() > t) {
            fa = root;
            root = root->left();
        } else {
            fa = root;
            root = root->right();
        }
    }
    return fa;
}

/*
  if the root has children and equals the t
  we can't just set the left
*/
template <class T>
bool Index_tree<T>::add(T t){
    Index_tree_node<T> *now = new Index_tree_node<T>(t);
    //find the father of the right node
    Index_tree_node<T> * fa = find_position(t);
    if (fa == NULL) {
        set_root(now);
    } else if(fa->data() < t){
        fa->set_left(now);
    } else {
        fa->set_right(now);
    }
    return true;
}


/*
  return: the numbers of direct childern, ie 0, 1 or 2
*/
template <class T>
int count_child(Tree_node<T> *fa){
    if (fa->left() != NULL && fa->right() != NULL){
        return 2;
    } else if (fa->left() != NULL || fa->right() != NULL){
        return 1;
    } else {
        return 0;
    }
}