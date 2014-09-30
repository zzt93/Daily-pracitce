#include <stdio.h>
#include <stddef.h>
#include <string.h>
#include <ctype.h>
#include <regex.h>  //standard of perl
#include "calc02.h"
#include "myStack.h"
#define BUFSIZE 100

char buf[BUFSIZE];
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
  priority in stack: ):7 *:5 /:5 +:3 -:3 (:1 '\n':0
  priority out of stack: (:8 *:6 /:6 +:4 -:4 ):2 '\n':0
  return the state of input
*/
size_t len;
int sp = 0;
char stack[len];//?
int transform(char *inputStr){
    int i = 0;
    //transform the input to postfix order
    for(; j < len; ++j){
        
    }
    //copy to buf
    i = len;
    for(; i > 0; --i){
        if(reversedBuf[i] != 0){
            ungetch(reversedBuf[i]);
            ungetch(' ');
        }
    }
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
    len = getline(&inputStr, &maxL , stdin);//how would getline behave if input is '1+2 EOF \n' --it seems EOF was ignored
	if (len == -1) {
		return EOF;
	}else {
		//make it reverse order
		state = transform(inputStr);
		if (!state){
			return '$';//flush the stack of previous input in order to start again
		}else{
            fprintf(stderr, "something wrong in transform: %s", inputStr);
        }
		return buf[--bufp];
	}
}
