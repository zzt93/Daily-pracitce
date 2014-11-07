#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>
#include <ctype.h>
#include "calc02.h"
#define BUFSIZE 100

char buf[2*BUFSIZE];
int bufp = 0;

int getch (void){
    return (bufp>0) ? buf[--bufp] : getNormal();
}

void ungetch (int c){
	if (bufp >= BUFSIZE)
	{
		printf("ungetch : too many characters\n");
	}
	else{
		buf[bufp++] = c;
	}
}

void ungets (char s[]) {
	int size = strlen(s);
	if (bufp+size >= BUFSIZE)
	{
		printf("ungets : too many characters\n");
	}
	else{
		ungetch(' ');//let to check
		while (size) {
			ungetch(s[--size]);
		}
	}
}

/*
  rule of priority: only the high priority can place on the low priority
  priority in stack: ):7 *:6 /:6 +:4 -:4 (:1 '\n':0
  priority out of stack: (:8 *:5 /:5 +:3 -:3 ):2 '\n':0
  return the state of input
*/
int spT = 0;
char stack[BUFSIZE];

int pushT(int c);
int popT();
int topT();
int flushToCharT(char *,char);
int sizeT();

int transform(char *inputStr, size_t len){
    char reversedBuf[2*len];
    int i = 0, j = 0;
    //transform the input to postfix order
    pushT('\n');
    char c;
    char topE;
    for(; i < len; ++i){
        c = inputStr[i];
        topE = topT();
        if (isdigit(c) || c == '.') {
            reversedBuf[j++] = inputStr[i];
        } else {
            pushT(' ');//in order to separate numbers
            if (c == '(') {
                pushT('(');
            } else if (isspace(c)) {//ignore the space
            } else if (c == ')'|| c == '\n' ) {
                j += flushToCharT(&reversedBuf[j], c);
            } else {//compare the priority of two operator
                while(topE == '*' || topE == '/'
                    || topE == c //means they are all + or -
                    || (topE == '+' && c == '-')
                    ||  (topE == '-' && c == '+')){
                    
                    reversedBuf[j++] = popT();
                    //update
                    topE = topT();
                }
                pushT(c);
            }
        }
    }
    if(sizeT() != 0){
        fprintf(stderr, "something wrong in transform");
        exit(1);
    }
    //copy to buf
    i = 2*len - 1;
    for(; i > 0; --i){
        if(reversedBuf[i] != 0){
            ungetch(reversedBuf[i]);
        }
    }
}
int sizeT(){
    return spT;
}
/*
  parameter: char *: destination of elements; char: the symbol of end
  return: the state of function
*/
int flushToCharT(char *des, char c){
    int i = 0;
    if(c == ')'){
        c = '(';
    }
    for(; stack[spT] != c; --spT){
        des[i++] = stack[spT];
    }
    pop();//pop the '(' or '\n' in the stack
    return i;
}
int pushT(int c){
    if(spT < BUFSIZE){
        stack[spT++] = c;
        return c;
    }else{
        fprintf(stderr,"stack is full");
        return -1;
    }
}
int popT(){
    if(spT > 0){
        return stack[--spT];
    }else{
        fprintf(stderr, "stack is empty");
        return -1;
    }
}
int topT(){
    int temp = pop();
    push(temp);
    return temp;
}

/*
  method: getNormal
  params: nil
  function: fill the buffer and return a char
  date: 2014.9.29
*/
int getNormal() {
	size_t maxL = 100;
	char *inputStr;
    int state;
    size_t len = getline(&inputStr, &maxL , stdin);//how would getline behave if input is '1+2 EOF \n' --it seems EOF was ignored
	if (len == -1) {
		return EOF;
	}else {
		//make it reverse order
		state = transform(inputStr, len);
		if (!state){
            fprintf(stderr, "something wrong in transform: %s", inputStr);
			return '$';//flush the stack of previous input in order to start again
		}
		return buf[--bufp];
	}
}
