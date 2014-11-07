#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "calc02.h"

#define MAXOP 100

int main(int argc, char const *argv[])
{
	int type;
	double op2;
	char s[MAXOP];
	extern int sp;
 	while ((type = getop(s)) != EOF)
	{
		switch (type){
		case NUMBER :
			push (atof (s));
			break;
		case IDENTIFIER :
			exFunction(s);
			break;
		case '@':
			printAll();
			break;
		case '\n':
			printTop();
			break;
		case '$':
			flush();
			fprintf(stderr, "error: wrong operand and flush the stack, try again");
			break;
		
		case '_':
			printf("%lf", copyTop());
			break;
		}
		if (sp >= 2) {
			switch (type) {
			case '%' :
				op2 = pop();
				if(op2) {
					push(fmod(pop(), op2)) ;
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
			case '~':
				swapTop();
				break;
			default:
				printf("error: no such command: %c", type);
			}
		}else if (sp == 1){
			if (type == '-'){
				push(-pop());
			}else {
				printf("error : no enought number to calculate\n");
			}
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
