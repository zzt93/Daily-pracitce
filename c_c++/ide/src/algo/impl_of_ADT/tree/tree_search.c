#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "union_node_tree.h"
#include "double_linked_list<double>.h"
#include "treeStack.h"

//for treeNode Stack
TNode *stack[SIZE];
int sp = 0;

TNode *pop(){
    if(sp > 0){
        return stack[--sp];
    } else {
        fprintf(stderr, "no elements");
    }
}
void push(TNode *t){
    if(sp < SIZE){
        stack[sp++] = t;
    } else {
        fprintf(stderr, "too many");
    }
}
int empty(){
    return sp == 0;
}

//for print the node
void printNode(TNode *root){
    switch(root->type){
        case DOUBLE:
            printf("%lf ", root->data.d);
            break;
        case STRING:
            printf("%s ", root->data.str);
            break;
        case INT:
            printf("%d ", root->data.i);
            break;
        case CHAR:
            printf("%c ", root->data.c);
            break;
        default:
            printf("no such type");
    }
}

//for list of expression: add at the tail, take at the head
Node head = {0, NULL, NULL};
Node *ph = &head;

int add(Node *node){
}
double next(Node *){
}

int main(int argc, char*argv[]){
}

/*
  make tree from the leaves to root
  return: the pointer to root
*/
TNode *make_RandomTree(){
}

TNode *make_tree(Data data[]){
}

void preorderRec(TNode *root){
    if (root != NULL){
        printNode(root);
        preorderRec(root->left);
        preorderRec(root->right);
    }
}
void preorderLoop(TNode *root){
    while(root != NULL || !empty()){
        while (root != NULL) {
            printNode(root);
            push(root);
            root = root->left;
        }
        //trace back
        root = pop()->right;
    }
}

void inorderRec(TNode *root){
    if(root != NULL){
        inorderRec(root->left);
        printNode(root);
        inorderRec(root->right);
    }
}
void inorderLoop(TNode *root){
    while(root != NULL || !empty()){
        while (root != NULL) {
            push(root);
            root = root->left;
        }
        //print the data
        root = pop();
        printNode(root);
        //trace back
        root = root->right;
    }
}
/*
  place the node at the right place
*/
void sort_tree(TNode *root, Data data, Type t){
    switch(t) {
        case STRING:{
            char *s = data.str;
            while(root != NULL){
                if(strcmp(s, root->data.str) > 0){
                    root = root->right;
                } else {
                    root = root->left;
                }
            }
            TNode *temp = malloc(sizeof(TNode));
            temp->data.str = s;
            temp->type = STRING;
            break;
        }
        case DOUBLE:{
            double d = data.d;
            while(root != NULL){
                if(d > root->data.d){
                    root = root->right;
                } else {
                    root = root->left;
                }
            }
            TNode *temp = malloc(sizeof(TNode));
            temp->data.d = d;
            temp->type = STRING;
 
            break;
        }
        case INT:{
            int i = data.i;
            while(root != NULL){
                if(i > root->data.i){
                    root = root->right;
                } else {
                    root = root->left;
                }
            }
            TNode *temp = malloc(sizeof(TNode));
            temp->data.i = i;
            temp->type = STRING;

            break;
        }
        case CHAR:{
            char c = data.c;
            while(root != NULL){
                if(c > root->data.c){
                    root = root->right;
                } else {
                    root = root->left;
                }
            }
            TNode *temp = malloc(sizeof(TNode));
            temp->data.c = c;
            temp->type = STRING;
            break;
        }
        default:
            fprintf(stderr, "no such type");
            break;
    }
}

void postOrderRec(TNode *root){
    if(root != NULL){
        postOrderRec(root->left);
        postOrderRec(root->right);
        printNode(root);
    }
}
void postOrderLoop(TNode *root){
    while (root != NULL || !empty()) {
        while (root->left != NULL) {
            push(root);
            root = root->left;
        }
        push(root);
        if (root->right == NULL) {
            printNode(pop());
            root = pop();
        } else {
            root = root->right;
            
        }

    }
}

void evaluation(TNode *root){
    if (root->type == STRING) {
        return;
    }

    //use the post order to analyze the tree

    //evaluate the expression
}