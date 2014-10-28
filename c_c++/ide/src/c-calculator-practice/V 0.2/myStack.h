//#define STACK_SIZE 128

extern char stack[];
extern int sp ;

int push(int a);
int pop();
int top();
int flushToChar(char *,char);
int size();