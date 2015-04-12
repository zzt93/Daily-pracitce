#include <stdio.h>
#include "calc02.h"
#define MAXVAL 100

int sp = 0;
double val [MAXVAL];

void push (double f){
	if (sp < MAXVAL)
	{
		val[sp++] = f;
	}
	else {
		printf("error :stack full in stack02.c, can't push %g\n", f);
	}
}

double pop (void){
	if (sp > 0)
	{
		return val [--sp];
	}
	else {
		printf("error : stack empty in stack02.c\n");
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

void printAll() {
	int copySp = sp;
	while (copySp){
		printf(" %lf ", val[--copySp]);
	}
}

double copyTop () {
	if (sp > 0) {
		return val[sp-1];
	}else {
		printf("stack is empty\n");
		return 0.0;//any better option	
	}
	
}

int swapTop () {
	if (sp > 1) {
		double tem = val[sp-1];
		val[sp-1] = val[sp-2];
		val[sp-2] = tem;
		return 1;//succeed
	}else {
		printf("stack is empty");
		return 0;//fail
	}
}

int flush () {
	sp = 0;
	return 1;
}

