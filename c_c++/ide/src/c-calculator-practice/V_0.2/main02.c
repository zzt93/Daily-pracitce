#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "calc02.h"

#define MAXOP 100

int main(int argc, char const *argv[])
{
    puts("(calculator v0.2)");
    puts("enter the expression:");
	int type;
	double op2;
	char s[MAXOP];
	extern int sp;
 	while ((type = getop(s)) != EOF)
	{
		if (sp >= 2) {
			switch (type) {
                case '%' :
                    op2 = pop();
                    if(op2) {
                        push(fmod(pop(), op2)) ;
                    }else 
					printf("\nError: Division by zero!");
                    continue;
                case '+' :
                    push (pop() + pop ());
                    continue;
                case '-' :
                    op2 = pop();
                    push (pop() - op2);
                    continue;
                case '*':
                    push (pop() * pop());
                    continue;
                case '/':
                    op2 = pop ();
                    if (op2 != 0.0)
                    {
                        push (pop() / op2);
                    }
                    else {
                        printf("error : zero divisor\n");
                    }
                    continue;
                case '~':
                    swapTop();
                    continue;
			}
		}else if (sp == 1){
			if (type == '-'){
				push(-pop());
                continue;
			}else if (type == '+' ||
                      type == '*' ||
                      type == '/'){
				printf("error : no enough number to calculate\n");
                continue;
			}
		}
        switch (type){
            case NUMBER :
                push (atof (s));
                continue;
            case IDENTIFIER :
                exFunction(s);
                continue;
            case '@':
                printAll();
                continue;
            case '\n':
                printTop();
                continue;
            case '$':
                flush();
                fprintf(stderr, "unknown operand and the stack is flushed, try again \n");
                continue;
            case '_':
                printf("%lf", copyTop());
                continue;
            default:
                fprintf(stderr, "no such command: %c\n", type);
        }
	}
	return 0;
}

void exFunction (char s[]) {
	double op2;
	
	if(!strcmp(s, "sin")){
		push(sin(pop()));
	}else if(!strcmp(s, "cos")) {
		push(cos(pop()));
	}else if (!strcmp(s, "exp")) {
		push(exp(pop()));
	}else if(!strcmp(s, "pow")) {
		op2 = pop();
		push(pow(pop(), op2));
	}else {
		printf("%s is not a supported function.\n", s);
	}
}
