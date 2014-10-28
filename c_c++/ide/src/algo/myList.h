#include <stddef.h>

typedef struct node Node;
struct node{
    double x;
    Node *last;
    Node *next;
};

extern Node head;
extern Node *ph;
extern Node *pe;
extern size_t size;

void flush(){
    ph = NULL;
}

int add(Node *);
int insert(int , Node *);
int hasNext(Node *);
int hasLast(Node *);
double next(Node *);
double last(Node *);