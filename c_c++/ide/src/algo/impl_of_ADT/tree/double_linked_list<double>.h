#include <stddef.h>

typedef struct node Node;
struct node{
    double x;
    Node *last;
    Node *next;
};

extern Node head;
extern Node tail;
extern Node *ph;
extern Node *pt;
extern size_t size;

void flush(){
    ph = NULL;
    pt = NULL;
}

int add(Node *);
double first();
double last();
int insert(int , Node *);

int hasNext(Node *);
int hasLast(Node *);
double nextE(Node *);
double lastE(Node *);