#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "union_node_tree.h"
#include "double_linked_list<double>.h"  //use it as a queue here
#include "treeStack.h"

//for treeNode Stack
TNode *stack[SIZE];
int sp = 0;

TNode *pop(){
    if(sp > 0){
        return stack[--sp];
    } else {
        fprintf(stderr, "no elements");
        return NULL;
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

/*
  make tree from the leaves to root
  return: the pointer to root
*/
TNode *make_RandomTree(){
    return NULL;
}

TNode *make_tree(Order order1, Data data1[], Order order2, Data data2[]){
    if (order1 == PREORDER && order2 == INORDER){
        
    } else if (order1 == INORDER && order2 == PREORDER){
        
    } else if (order1 == INORDER && order2 == POSTORDER){
    } else if (order1 == POSTORDER && order2 == INORDER){
    } else {
        puts("Wrong order, can't make tree");
    }
    return NULL;
}

void preorderRec(TNode *root){
    if (root != NULL){
        printNode(root);
        preorderRec(root->left);
        preorderRec(root->right);
    }
}
void preorderLoop(TNode *root){
    /*version 1: */
    //the outer loop has two function: judge the end ; find right
    while(root != NULL || !empty()){//how to judge a recursion is done? The stack is empty and root == NULL
        while (root != NULL) {//find left
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
TNode *print_stack[SIZE];
int p_sp = 0;

void push_p(TNode *t){
    if (p_sp < SIZE) {
        print_stack[p_sp++] = t;
    } else {
        puts("stack is full");
    }
}
TNode *pop_p(){
    if (p_sp > 0) {
        return print_stack[--p_sp];
    } else {
        puts("stack is empty");
    }
}

int count_stack[SIZE];
int c_sp = 0;

void push_c(int i){
    if (p_sp < SIZE) {
        count_stack[c_sp++] = i;
    } else {
        puts("stack is full");
    }
}
int pop_c(){
    if (c_sp > 0) {
        return count_stack[--c_sp];
    } else {
        puts("stack is empty");
    }
}
void postOrderLoop(TNode *root){
    while (root != NULL || !empty()) {
        //find the left-most node
        while (root != NULL) {
            push(root);
            //version 2:
            push_c(0);
            //---
            root = root->left;
        }
        /*version 1:
        TNode *temp = pop();
        push_p(temp);
        root = temp->right;
        if(root == NULL){
            while (p_sp) {
                printNode(pop_p());
            }
        }
        */
        TNode *temp = pop();
        int count = pop_c();
        if (count == 0) {
            push(temp);
            push_c(++count);
            root = temp->right;
        } else {
            printNode(temp);
        }

    }
}

//for list of expression: add at the tail, take at the head
//using as queue
Node head = {0, NULL, NULL};
Node tail = {0, NULL, NULL};
Node *pt = &tail;
Node *ph = &head;

int add(Node *node){
    return 0;
}
double last(){
    return 0.0;
}


void evaluation(TNode *root){
    if (root->type == STRING) {
        return;
    }

    //use the post order to analyze the tree

    //evaluate the expression
}

int main(int argc, char*argv[]){
    TNode c = (TNode){{'C'}, CHAR, NULL, NULL};
    c.data.c = 'C';
    
    TNode b =  (TNode){{'C'}, CHAR, NULL, NULL};
    b.data.c = 'B';
    
    TNode e = (TNode){{'C'}, CHAR, NULL, NULL};
    e.data.c = 'E';
    
    TNode d = (TNode){{'D'}, CHAR, NULL, NULL};
    d.data.c = 'D';
    
    TNode a = (TNode){{'A'}, CHAR, NULL, NULL};
    a.data.c = 'A';

    b.right = &c;
    a.left = &b;
    d.left = &e;
    a.right = &d;

    preorderRec(&a);
    puts("");
    inorderRec(&a);
    puts("");
    postOrderRec(&a);
    puts("");
    preorderLoop(&a);
    puts("");
    inorderLoop(&a);
    puts("");
    postOrderLoop(&a);
    
    return 0;
}

