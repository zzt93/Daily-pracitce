#include <iostream>
#include "BST.hpp"

template <class E, class K>
bool BST<E, K>::remove(BSTNode<E, K>* root, const K& k){
    BSTNode<E, K> *temp = search(root, k);
    if (temp == NULL){
        return false;
    }
    if (temp.left == NULL && temp.right == NULL){
        
    } else if (temp.left == NULL){
        
    }
}

int main(int argc, char *argv[])
{
    
    return 0;
}

