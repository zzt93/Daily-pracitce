#define NUMBER '0'
#define IDENTIFIER '1'
void exFunction(char s[]);
//getop02.c
int getop (char s[]);
void accumulateN (char c, char s[]);
//getch02.c
int getch (void);
void ungetch (int);
void ungets (char s[]);
void getNormal();
int findCentral(char *, size_t);
//stack02.c
extern int sp;
void push (double);
double pop (void);
void printTop ();
void printAll();
int flush ();
double copyTop();
int swapTop();
