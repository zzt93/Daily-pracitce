#include <iostream>
#include <fstream>
#include "index_bst.hpp"

int main(int argc, char *argv[])
{
    
    return 0;
}

bool Index_tree::has(T t){
}

bool deleteNode(T t){
    if (!has(t)) {
        return false;
    } else {
        Index_tree_node * temp = find(t);
        if(temp->left){
            //the node is leaf
        } else if(){
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
Index_tree_node * find(T t){
}
