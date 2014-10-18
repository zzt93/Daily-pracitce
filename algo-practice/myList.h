#include <stddef.h>

struct node{
    double x;
    Node *last;
    Node *next;
};
typedef struct node Node;

extern Node head;
extern Node *ph;
extern Node *pe;
extern size_t size;

bool flush(){
    head = null;
}

int add(Node *);
int insert(int , Node *);
int hasNext(Node *);
int hasLast(Node *);
double next(Node *);
double last(Node *);