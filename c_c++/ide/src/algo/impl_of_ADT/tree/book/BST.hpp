#include <iostream>

template <class E, class K>
class BST;

template <class E, class K>
class BSTNode {
private:
    E data;
    K key;
    BSTNode<E, K> *left, *right;
    
    BSTNode(const BSTNode& n){
        data = n.get_data();
        key = n.get_key();
        if (n.left != NULL){
            left = new BSTNode<E, K>(n.*left);
        }
        if (n.right != NULL){
            right = new BSTNode<E, K>(n.*right);
        }
    }
public:
    BSTNode():left(NULL), right(NULL) {
    }
    BSTNode(E data, K key, BSTNode<E, K> *l = NULL, BSTNode<E, K> *r= NULL){
        this->data = data;
        this->key = key;
        left = l;
        right = r;
    }
    virtual ~BSTNode(){
        delete left;
        delete right;
    }
    
    void set_data(E d){
        this->data = d;
    }
    E get_data(){
        return data;
    }
    void set_key(K k){
        key = k;
    }
    K get_key(){
        return key;
    }
    
    bool operator < (BSTNode<E, K>& n);
    bool operator > (BSTNode<E, K>& n);
    bool operator == (BSTNode<E, K>& n);

    friend class BST<E, K>;
};

template <class E, class K>
bool BSTNode<E, K>::operator < (BSTNode<E, K>& n){
    return get_key() < n.get_key();
}
template <class E, class K>
bool BSTNode<E, K>::operator > (BSTNode<E, K>& n){
    return get_key() > n.get_key();
}
template <class E, class K>
bool BSTNode<E, K>::operator == (BSTNode<E, K>& n){
    return get_key() == n.get_key();
}

template <class E, class K>
class BST {
private:
    BSTNode<E, K> *root;

    BSTNode<E, K> *search(BSTNode<E, K> *ptr, const K k);
    void make_empty(BSTNode<E, K> * ptr);
    void print_tree(BSTNode<E, K> ptr);
    bool insert(BSTNode<E, K> * ptr, BSTNode<E, K>*);
    bool remove(BSTNode<E, K> * prt, const K& k);
    
    BSTNode<E, K> * copy(const BSTNode<E, K> *ptr);
    BSTNode<E, K> * max(BSTNode<E, K> *ptr) const;
    BSTNode<E, K> * min(BSTNode<E, K> *ptr) const;
    template <class Out>
    void inorder(Out o, BSTNode<E, K>*);
public:
    BST():root(NULL){}
    BST(K value);
    virtual ~BST(){
        delete root;
    }
    BST(const BST& tree){
        root = new BSTNode<E, K>(tree.root);
    }

    bool exist(const K k) const {
        return search(root, k) != NULL;
    }

    BST<E, K>& operator = (BST<E, K>& tree);
    void make_empty(){
        make_empty(root);
        root = NULL;
    }
    void print_tree() const {
        print_tree(root);
    }
    bool insert(const E& e, const K& k){
        return insert(root, new BSTNode<E, K>(e, k));
    }
    bool remove(const K& x){
        return remove(root, x);
    }

    template <class Out>
    void in_order(Out o){
        inorder(o, root);
    }
};

template <class E, class K>
template <class Out>
void inorder(Out o, BSTNode<E, K>* root){
    while (root != NULL){
        //TODO
        *o++ = root;
    }
}

