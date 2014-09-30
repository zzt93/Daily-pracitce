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


/*ignore the parenthesis and the content in it
  priority:; +, - ; *, /; -(unary), fun()*/
regex_t operand; 
const char *patternO = "[ -]?\\s*(\\(\\s*-)*[0-9]*[.]?[0-9]*[\\s\\)]*";

int compileReg() {
	if (regcomp(&operand, patternO, RED_EXTENDED)){
		fprintf(stderr, "%s: bad pattern: '%s' \n", argv[0], patternS);
		return 0;
	}
	return 1;
}
/*
 *judge whether to recur by count the operand
 *just one: finished
 *more than one: continue
 */
int judgeR (char *b, int *i) {
	size_t nmatch = 2;
	regmatch_t matchptr[nmatch];
	int matchR = regexec(&operand, b, nmatch, matchptr, 0);

	if(matchR) {
		fprintf(stderr, "no operand: %s", b);
		return 0;
	}
	if (matchptr[1].rm_so == -1) {//means the second not match: just one operand
		i = matchptr[0].rm_so;
		return 1;
	}else {
		i = matchptr[0].rm_eo;
		return 2;
	}
}
/*
 *check the buf[start] to buf[end] whether have a number or digit
 */
int hasNum (char* partStr, int start, int end){
	while (start <= end) {
		if (isdigit(partStr[start]) || partStr[start] == '.'){
			return start;
		}
		++start;
	}
	return -1;
}
int sp = 0;
char stack[BUFSIZE];
int matchParen(char *contentBuf, size_t len, int *start, int *end){
    //char checkParenBuf[BUFSIZE];
    size_t i = 0;
    int hasParen = 0;
    int countPop = 0;
    *end = -1;
    *start = 0;
    //first part: check whether the parenthesis is in pairs
    for(; i < len; ++i){
        if(contentBuf[i] == '('){
            hasParen = 1;
            if(push(i) == -1){//the stack is full 
                return -1;
            }
        }else if(contentBuf[i] == ')'){//the second part: find the first pair legal ()
            hasParen = 1;
            countPop++;
            if(countPop == 1){
                *end = i;
                if((*start = pop()) == -1){//means that the number of ')' is larger than that of ')'
                    return -1;
                }
            }
            if(pop() == -1){
                return -1;
            }
        }
    }
    if(sp != 0){//means that the number of '(' is larger than that of ')'
        return -1;
    }
    if(!hasParen){//no parenthesis
        return 1;
    }
    if(*start < *end){
        return 0;
    }
}
int push(int c){
    if(sp < STACK_SIZE){
        stack[sp++] = c;
        return c;
    }else{
        fprintf(stderr,"stack is full");
        return -1;
    }
}
int pop(){
    if(sp > 0){
        return stack[--sp];
    }else{
        fprintf(stderr, "stack is empty");
        return -1;
    }
}

int state = 0;
int findCentral (char *inputStr, size_t size) {
	int lastIsNum = 0;
	char recurBuf[size+1];//add a place for '\0'
	char c, tem;
	char case1c;
	int i = 0;
	size_t nmatch = 1;
	regmatch_t matchptr[nmatch];
	int matchC, start, end;
	strncpy(recurBuf, inputStr, size);
	recurBuf[size] = '\0';
	//cases not to recur: 1; (-1); -1.1  --> just one operand
	//cases needing recursion: 1+2; 1.1*2.2; (-1)*2; -1*2; -(1+2) ; ((1+2)*3)-4; 1*(2-3); (1+2)*(3-4)
	switch (judgeR (recurBuf, &i)) {
        case 1:	//mean one operand and no recursion needed
            while (i < size) {
                case1c = recurBuf[i++];
                if (case1c != '(' && case1c != ')' && !isspace(case1c)) {// space should be adjusted
                    ungetch(case1c);
                }
                if (case1c == '-') {
                    ungetch(' ');
                }
            }
            ungetch(' ');
            return 1;
        case 2:	//mean more than one
            //two situations: useful parenthesis ;; no () or useless ()
            matchC = matchParen(recurBuf, size+1,  &start, &end);
            
            if(matchC == -1){
                fprintf(stderr, "parenthesis are not in pair: %s, %s", recurBuf, inputStr);
                exit(1);
            }
            //means match! -- () exists
            else if (matchC == 0) {
                
                //useful ()
                if ( hasNum(recurBuf, 0, start-1) != -1 || hasNum(recurBuf, end+1, size-1) != -1 ) {
                    while (i < start){
                        c = recurBuf[i++]; 
                        if ((c == '+' 
                                || c == '-') && hasNum(recurBuf, 0, i-1)!=-1 ){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                    i = end+1;
                    while (i < size){
                        c = recurBuf[i++]; 
                        if ((c == '+' 
                                || c == '-') && hasNum(recurBuf, i, size-1)!=-1 ){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                    i = 0;
                    while (i < start){
                        c = recurBuf[i++];
                        if ((c == '*' || c == '/') && hasNum(recurBuf, 0, i-1)!=-1){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                    i = end+1;
                    while (i < size){
                        c = recurBuf[i++];
                        if ((c == '*' || c == '/') && hasNum(recurBuf, i, size-1)!=-1){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                }
                // useless ()
                else {
                    i = start;
                    while (--i >=0){
                        if (recurBuf[i] == '-'){
                            ungetch('-');
                            break;
                        }
                    }
                    ungetch(' ');
                    state &= findCentral(&recurBuf[start+1],end-start-2); 
                }
                return 0;//means the input is not right
            ufRecursion:
                state &= findCentral (&recurBuf[i], size-i-1); 
                state &= findCentral (recurBuf, i);

            }else { // no ()
                i = 0;
                while (i < size){
                    c = recurBuf[i++]; 
                    if ((c == '+' 
                            || c == '-') && hasNum(recurBuf, 0, i-1)!=-1 ){
                        ungetch(c);
                        ungetch(' ');
                        goto nRecursion;
                    }
                }
                i = 0;
                while (i < size){
                    c = recurBuf[i++];
                    if ((c == '*' || c == '/') && hasNum(recurBuf, 0, i-1)!=-1){
                        ungetch(c);
                        ungetch(' ');
                        goto nRecursion;
                    }
                }
                return 0;//means input is not right
            nRecursion:
                state &= findCentral (&recurBuf[i], size-i-1);
                state &= findCentral (recurBuf, i);//the total size become size-1 for a central operator is pushed in stack

            }
            return 1;
        default:
            return 0;
	}

}


int getNormal() {
	size_t maxL = 100;
	char *inputStr;
	size_t len;
    len = getline(&inputStr, &maxL , stdin);//how would getline behave if input is '1+2 EOF \n' --it seems EOF was ignored
	if (len == -1) {
		return EOF;
	}else {
		//make it reverse order
		state = compileReg();
		ungetch('\n');
		state &= findCentral(inputStr, len-1);//len-1 mean not including the '\n'
		regfree(&operand);
		if (!state){
			return '$';//flush the stack of previous input in order to start inputting again
		}else{
            fprintf(stderr, "findCentral doesn't work fine on: %s", inputStr);
        }
		return buf[--bufp];
	}
}
