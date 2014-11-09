#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>
#include <assert.h>
#include <ctype.h>
#include <regex.h>  //standard of perl
#include "calc02.h"
#include "myStack.h"
#define BUFSIZE 100
#define STACK_SIZE 100

/*
  TODO: test
*/

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
const char *patternO = "[0-9]+|[.][0-9]+|[0-9]+[.][0-9]+";

int compileReg(char *in) {
	if (regcomp(&operand, patternO, REG_EXTENDED)){
		fprintf(stderr, "%s: bad pattern: '%s' \n", in, patternO);
		return 0;
	}
	return 1;
}
/*
 *judge whether to recur by count the operand
 *just one: finished
 *more than one: continue
 */
int judgeR (const char *b, int *in) {
	size_t nmatch = 3;
	regmatch_t matchptr[nmatch];
    int count = 0;
    //const char * const dub = b;
    int matchR = 0;
    int startOf1Num = -1;
    int finishOf1Num = -1;
    
    while(!matchR){
        int i = 0;
        matchR = regexec(&operand, b, nmatch, matchptr, 0);
        if(matchR) {//matchR == 0 means match
            //printf("no operand: %s", dub);
            break;
        }
        count++;
        for(; i < nmatch; ++i){
            if (matchptr[i].rm_so == -1){
                //puts("break");
                break;
            }
            //start = matchptr[i].rm_so + (b-dub);
            //finish = matchptr[i].rm_eo + (b-dub);
            //printf("$%d is ", i);
            //printf("'%.*s' (byte %d:%d)\n", finish-start, dub+start, start, finish);
        }
        if(count == 1){
            startOf1Num = matchptr[0].rm_so;
            finishOf1Num = matchptr[0].rm_eo;
        }
        b += matchptr[0].rm_eo;
    }
    printf("count is %d\n", count);
    fflush(stdin);
    assert(count > 0);//if count < 0, the program fail
	if (count == 1) {//means the second not match: just one operand
        *in = startOf1Num;
		return 1;
	}else {
        *in = finishOf1Num;
		return 2;
	}
}
/*
 *check the interval of [start, end) of buf whether have a number or digit

 params: a string of expression -- char *
         the int to record the start of string -- int
         the int to record the end of string -- int

 return value: -1 -- represent it has no number
               other -- the start of number
 */
int hasNum (const char* partStr, int start, int end){
    if(start >= end) {
        return -1;
    }
	while (start < end) {
		if (isdigit(partStr[start]) || partStr[start] == '.'){
			return start;
		}
		++start;
	}
    return -1;
}
int spParen = 0;
char stack[BUFSIZE];
/*
  params: a char pointer; the size of buf; the start index of pattern; the ending index of pattern
  return value: the interval of the pattern: [start, end)
*/
int matchParen(char *contentBuf, size_t len, int *start, int *end){
    size_t i = 0;
    int hasParen = 0;
    int countPop = 0;
    *end = -1;
    *start = 0;
    //first part: check whether the parenthesis is in pairs
    for(; i < len; ++i){
        if(contentBuf[i] == '('){
            hasParen = 1;
            if(pushParen(i) == -1){//the stack is full
                return -1;
            }
        }else if(contentBuf[i] == ')'){//the second part: find the first pair legal ()
            hasParen = 1;
            countPop++;
            if(countPop == 1){//the first pair of parenthesis
                *end = i+1;
                if((*start = popParen()) == -1){//means that there is no a '(' is before first ')'
                    return -1;
                }
            } else if(popParen() == -1){
                return -1;
            }
        }
    }
    if(spParen != 0){//means that the number of '(' is larger than that of ')'
        return -1;
    }
    if(!hasParen){//no parenthesis
        return 1;
    }
    if(*start < *end){//mean find a pair of parenthesis
        return 0;
    } else {
        //TODO: check
        return 2;
    }
}
int pushParen(int c){
    if(spParen < STACK_SIZE){
        stack[spParen++] = c;
        return c;
    }else{
        fprintf(stderr,"parenthesis stack is full \n");
        return -1;
    }
}
int popParen(){
    if(spParen > 0){
        return stack[--spParen];
    }else{
        fprintf(stderr, "parenthesis stack is empty \n");
        return -1;
    }
}

int state = 0;
int findCentral (char *inputStr, size_t size) {
	char recurBuf[size+1];//add a place for '\0'
	char c;// tem;
	char case1c;
	int i = 0;
	int matchC, start, end;
	strncpy(recurBuf, inputStr, size);
    recurBuf[size] = '\0';

    // TODO : tested --  1+2 , (1+2)
    //        tested --  1.2 + 2.2
    //        failed --  (1+2) * 3 -- wrong result 3
	//cases not to recur: 1; (-1); -1.1  --> just one operand
	//cases needing recursion: 1+2; 1.1*2.2; (-1)*2; -1*2; -(1+2) ; ((1+2)*3)-4; 1*(2-3); (1+2)*(3-4)
	switch (judgeR (recurBuf, &i)) {
        case 1:	//mean one operand and no recursion needed
        {
            i = size-1;
            while (i >= 0) {
                case1c = recurBuf[i--];
                if (case1c == '-') {
                    ungetch(' ');
                }
                if (case1c != '(' && case1c != ')' && !isspace(case1c)) {// space should be adjusted
                    ungetch(case1c);
                }
            }
            ungetch(' ');
            return 1;
        }
        case 2:	//mean more than one
            //two situations: useful parenthesis ;; no () or useless ()
        {
            const int dui = i;
            matchC = matchParen(recurBuf, size,  &start, &end);
            if(matchC == -1){
                fprintf(stderr, "parenthesis are not in pair: %s, %s \n", recurBuf, inputStr);
                puts("You seems just entering part of expression, entering more?");
            }
            //means match! -- () exists
            else if (matchC == 0) {
                //useful ()
                if ( hasNum(recurBuf, 0, start-1) != -1 || hasNum(recurBuf, end, size) != -1 ) {
                    while (i < start){
                        c = recurBuf[i++];
                        if ((c == '+'
                                || c == '-') && hasNum(recurBuf, 0, i-1)!=-1 ){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                    i = end;
                    while (i < size){
                        c = recurBuf[i++];
                        if ((c == '+'
                                || c == '-') && hasNum(recurBuf, i, size)!=-1 ){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                    i = dui;
                    while (i < start){
                        c = recurBuf[i++];
                        if ((c == '*' || c == '/') && hasNum(recurBuf, 0, i-1)!=-1){
                            ungetch(c);
                            ungetch(' ');
                            goto ufRecursion;
                        }
                    }
                    i = end;
                    while (i < size){
                        c = recurBuf[i++];
                        if ((c == '*' || c == '/') && hasNum(recurBuf, i, size)!=-1){
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
                    state &= findCentral(&recurBuf[start+1], end-start-2);
                    return 1;
                }
                return 0;//means the input is not right
            ufRecursion:
                state &= findCentral (&recurBuf[i], size-i);
                state &= findCentral (recurBuf, i-1);

            }// no ()
            else if (matchC == 1){
                //i = 0;
                while (i < size){
                    c = recurBuf[i++];
                    if ((c == '+'
                            || c == '-') && hasNum(recurBuf, 0, i-1)!=-1 ){
                        ungetch(c);
                        ungetch(' ');
                        goto nRecursion;
                    }
                }
                i = dui;
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
                state &= findCentral (&recurBuf[i], size-i);
                state &= findCentral (recurBuf, i-1);//the total size become less than size-1

            } else {
                printf("bad in matchParen: %d \n", matchC);
                return 0;
            }
            return 1;
        }
        default:
            return 0;
	}

}


int getNormal() {
	size_t maxL = 100;
	char *inputStr;
	size_t len;
	inputStr = malloc(maxL+1);
    len = getline(&inputStr, &maxL , stdin);//how would getline behave if input is '1+2 EOF \n' --it seems EOF was ignored
	if (len == -1) {
		return EOF;
	}else {
		//make it reverse order
		state = compileReg(inputStr);
		ungetch('\n');
		state &= findCentral(inputStr, len-1);//len-1 mean not including the '\n'
		regfree(&operand);
		if (!state){
			fprintf(stderr, "findCentral doesn't work fine on: %s", inputStr);
			return '$';//flush the stack of previous input in order to start inputting again
		}
		return getch();//buf[--bufp];//encapsulation
	}
}
