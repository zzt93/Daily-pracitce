#include <stdio.h>
#include <stdlib.h>
#incldue <myList.h>

/* algorithm of max of sub-array
   c implements
   author:zzt
   date: 2014.10.16
*/

Node head = {0, NULL, NULL};
Node *ph = &head;
Node *pe = &head;
size_t size = 0;

int readData(char *fname){
    char str[10];
    char *x;
    FILE *file = fopen(fname, "r");
    if(file == NULL){
        fprintf(stderr, "can't open file");
        exit(1);
    }
    while((x = fgets(file, 10, str)) != NULL){
        Node *a = (Node *)malloc(sizeof(Node));
        a->x = atoi(str);
        add(a);
    }
    fclose(fname);
}

int main(int argc, char *argv[]){
    if(argc == 1){
        puts("Usage: maxOfSum data.txt");
        exit(0);
    } else {
        readData(argv[1]);
    }
    //begin the algorithm
}
int insert(int i, Node* a){
    //update the size
    ++size;
}

int hasNext(Node *a){
    return (a->next == NULL);
}
double next(Node *a){
    return a->next;
}
double last(Node *a){
    return a->last;
}
int hasLast(Node *a){
    return (a->last == NULL);
}

int add(Node *a){
    //update the backword list
    a->last = end;
    end = a;
    //update the forward list
    a->last->next = a;
    a->next = NULL;
    //update the size
    size++;
}