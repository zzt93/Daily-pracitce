//#define STACK_SIZE 128

extern char stack[];
extern int sp ;

int pushParen(int a);
int popParen();
int top();
int flushToChar(char *,char);
int size();