#define SIZE 100

extern TNode *stack[];
extern int sp;

TNode *pop();
void push(TNode *t);
int empty();