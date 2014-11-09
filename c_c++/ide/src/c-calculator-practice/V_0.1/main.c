#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "calc.h"

#define MAXOP 100

int main(int argc, char const *argv[])
{
	puts("(calculator v0.1):");
	puts("enter the postfix expression");
	int type;
	double op2;
	char s[MAXOP];

 	while ((type = getop(s)) != EOF)
	{
		switch (type){
			case NUMBER :
				push (atof (s));
				break;
			case IDENTIFIER :
				exFunction(s);
				break;
			case '%' :
				op2 = pop();
				if(op2) {
					push(fmod(pop(), op2));
				}else 
              	    printf("\nError: Division by zero!"); 
          	    break; 
			case '+' :
				push (pop() + pop ());
				break;
			case '-' :
				op2 = pop();
				push (pop() - op2);
				break;
			case '*':
				push (pop() * pop());
				break;
			case '/':
				op2 = pop ();
				if (op2 != 0.0)
				{
					push (pop() / op2);
				}
				else {
					printf("error : zero divisor\n");
				}
				break;
			case '\n':
				printTop();
				break;
			case '$':
				flush();
				break;
			default :
				printf("error : unknown command %s\n", s);
				break;
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
