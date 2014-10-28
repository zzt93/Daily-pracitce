#include <stdio.h>
#include "calc.h"
#define MAXVAL 100

int sp = 0;
double val [MAXVAL];

void push (double f){
	if (sp < MAXVAL)
	{
		val[sp++] = f;
	}
	else {
		printf("error :stack full , can't push %g\n", f);
	}
}

double pop (void){
	if (sp > 0)
	{
		return val [--sp];
	}
	else {
		printf("error : stack empty\n");
		return 0.0;
	}
}

void printTop () {
	if (sp > 0) {
		printf("The top of stack is \t%.8g\n", val[sp-1]);
	}else {
		printf("stack is empty\n");	
	}
}



int flush () {
	sp = 0;
	return 1;
}

