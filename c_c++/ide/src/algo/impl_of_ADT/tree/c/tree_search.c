#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
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

int data_comp(Type type, Data d1, Data d2){
    switch(type) {
        case DOUBLE:
            if (d1.d > d2.d){
                return 1;
            } else if (d1.d == d2.d){
                return 0;
            } else {
                return -1;
            }
        case STRING:
            return strcmp(d1.str, d2.str);
        case INT:
            if (d1.i > d2.i){
                return 1;
            } else if (d1.i == d2.i){
                return 0;
            } else {
                return -1;
            }
        case CHAR:
            if (d1.c > d2.c){
                return 1;
            } else if (d1.c == d2.c){
                return 0;
            } else {
                return -1;
            }
      
        default:
            puts("no such type");
            break;
    }
    return NULL;
}

/*
  make tree from the leaves to root
  return: the pointer to root
*/
TNode *make_RandomTree(){
    TNode *root = malloc(sizeof(TNode));
    srand(time(0));
    int a = rand();
    root->data.i = a;
    return NULL;
}

TNode *make_tree(Type type, Order order1, Data data1[], int size1,  Order order2, Data data2[], int size2){
    printf("size1:%d, size2:%d\n", size1, size2);
    if (size1 == size2 && size1 == 1 || size1 == 0) {
        if (size1 == 0){
            return NULL;
        }
        TNode * temp =  malloc(sizeof(TNode));
        temp->data = data1[0];
        temp->left = NULL;
        temp->right = NULL;
        temp->type = type;
        return temp;
    } else if (size1 != size2){
        printf("size of two data are different, size1:%d, size2:%d \n", size1, size2);
    }

    int i = 0;
    if (order1 == PREORDER && order2 == INORDER){
        while (i < size2) {
            if(data_comp(type, data1[0], data2[i]) == 0){
                TNode *root = malloc(sizeof(TNode));
                root->data = data1[0];
                root->left = make_tree(type, order1, &data1[1], i, order2, data2, i);
                root->right = make_tree(type, order1, &data1[i+1], size1-i-1, order2, &data2[i+1], size1-i-1);
                root->type = type;
                return root;
            }
            ++i;
        }
    } else if (order1 == INORDER && order2 == PREORDER){
        while (i < size1) {
            if(data_comp(type, data2[0], data1[i]) == 0){
                TNode *root = malloc(sizeof(TNode));
                root->data = data2[0];
                root->left = make_tree(type, order1, data1, i, order2, &data2[1], i);
                root->right = make_tree(type, order1, &data1[i+1], size1-i-1, order2, &data2[i+1], size1-i-1);
                root->type = type;
                return root;
            }
            ++i;
        }

    } else if (order1 == INORDER && order2 == POSTORDER){
         while (i < size1) {
            if(data_comp(type, data2[size2-1], data1[i]) == 0){
                TNode *root = malloc(sizeof(TNode));
                root->data = data2[size2-1];
                root->left = make_tree(type, order1, data1, i, order2, data2, i);
                root->right = make_tree(type, order1, &data1[i+1], size1-i-1, order2, &data2[i], size1-i-1);
                root->type = type;
                return root;
            }
            ++i;
         }

    } else if (order1 == POSTORDER && order2 == INORDER){
        while (i < size2) {
            if(data_comp(type, data1[size2-1], data2[i]) == 0){
                TNode *root = malloc(sizeof(TNode));
                root->data = data1[size1-1];
                root->left = make_tree(type, order1, data1, i, order2, data2, i);
                root->right = make_tree(type, order1, &data1[i], size1-i-1, order2, &data2[i+1], size1-i-1);
                root->type = type;
                return root;
            }
            ++i;
        }
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
    while (root != NULL) {
        if (data_comp(t, data, root->data) > 0) {
            root = root->right;
        } else {
            root = root->left;
        }

    }
    root = malloc(sizeof(TNode));
    root->data = data;
    root->type = t;
    return;
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
        return NULL;
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
        return 0;
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
    /*    TNode c = (TNode){{'C'}, CHAR, NULL, NULL};
    c.data.c = 'C';
    
    TNode b =  (TNode){{'C'}, CHAR, NULL, NULL};
    b.data.c = 'B';
    
    TNode e = (TNode){{'C'}, CHAR, NULL, NULL};
    e.data.c = 'E';
    
    TNode d = (TNode){{'D'}, CHAR, NULL, NULL};
    d.data.c = 'D';
    
    TNode a = (TNode){{'A'}, CHAR, NULL, NULL};
    a.data.c = 'A';
    */
    /*
    b.right = &c;
    a.left = &b;
    d.left = &e;
    a.right = &d;
    */
    Data pre_data[5] = {
        {.c = 'A'},
        {.c = 'B'},
        {.c = 'C'},
        {.c = 'D'},
        {.c = 'E'},
    };
    Data in_data[5] = {
        {.c = 'B'},
        {.c = 'C'},
        {.c = 'A'},
        {.c = 'E'},
        {.c = 'D'},
    };
    Data post_data[5] = {
        {.c = 'C'},
        {.c = 'B'},
        {.c = 'E'},
        {.c = 'D'},
        {.c = 'A'},
    };

    TNode * root = make_tree(CHAR, PREORDER, pre_data, 5, INORDER, in_data, 5);
    preorderRec(root);
    puts("");
    inorderRec(root);
    puts("");
    postOrderRec(root);
    puts("");
    preorderLoop(root);
    puts("");
    inorderLoop(root);
    puts("");
    postOrderLoop(root);
    
    return 0;
}

