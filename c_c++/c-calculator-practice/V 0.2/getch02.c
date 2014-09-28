#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <regex.h>  //standard of perl
#include "calc02.h"
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

regex_t conParen;
regex_t operand;
const char *patternC = "\\(.*\\)"; //--> (...) 
const char *patternO = "^[ -]?\\s*(\\(\\s*-)*[0-9]*[.]?[0-9]*[\\s\\)]*$";

int compileReg() {
	if (regcomp(&conParen, patternC, RED_EXTENDED)||
	    regcomp(&operand, patternO, RED_EXTENDED)){
		fprintf(stderr, "%s: bad pattern: '%s'or'%s' \n", argv[0], patternS, patternC);
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
	if (matchptr[1].rm_so == -1) {//means the second not match
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
int hasNum (char* buf, int start, int end){
	while (start <= end) {
		if (isdigit(buf[start]) || buf[start] == '.'){
			return start;
		}
		++start;
	}
	return -1;
}
int state = 0;
int findCentral (char *tembuf, size_t size) {
	int lastIsNum = 0;
	char buf[size+1];//add a place for '\0'
	char c, tem;
	char case1c;
	int i = 0;
	size_t nmatch = 1;
	regmatch_t matchptr[nmatch];
	int matchC, start, end;
	strncpy(buf, tembuf, size);
	buf[size] = '\0';
	//cases not to recur: 1; (-1); -1.1  --> just one operand
	//cases needing recursion: 1+2; 1.1*2.2; (-1)*2; -1*2; -(1+2) ; ((1+2)*3)-4; 1*(2-3)
	switch (judgeR (buf, &i)) {
	case 1:	//mean one operand and no recursion needed
		while (i < size) {
			case1c = buf[i++];
			if (case1c != '(' && case1c != ')' && !isspace(case1c)) {// space shoulde be adjusted
				ungetch(case1c);
			}
			if (case1c == '-') {
				ungetch(' ');
			}
		}
		ungetch(' ');
		return 1;
	case 2:	//mean more than one
		//two situations: useful parenthesis; no () or useless ()
		matchC = regexec(&conParen, buf, nmatch, matchptr, 0);
		//means match! -- ()
		if (matchC == 0) { 

			start = matchptr[0].rm_so;
			end = matchptr[0].rm_eo;
			//useful ()
			if ( hasNum(buf, 0, start-1) != -1 || hasNum(buf, end+1, size-1) != -1 ) {
				while (i < start){
					c = buf[i++]; 
					if ((c == '+' 
					     || c == '-') && hasNum(buf, 0, i-1)!=-1 ){
						ungetch(c);
						ungetch(' ');
						goto ufRecursion;
					}
				}
				i = end+1;
				while (i < size){
					c = buf[i++]; 
					if ((c == '+' 
					     || c == '-') && hasNum(buf, i, size-1)!=-1 ){
						ungetch(c);
						ungetch(' ');
						goto ufRecursion;
					}
				}
				i = 0;
				while (i < start){
					c = buf[i++];
					if ((c == '*' || c == '/') && hasNum(buf, 0, i-1)!=-1){
						ungetch(c);
						ungetch(' ');
						goto ufRecursion;
					}
				}
				i = end+1;
				while (i < size){
					c = buf[i++];
					if ((c == '*' || c == '/') && hasNum(buf, i, size-1)!=-1){
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
					if (buf[i] == '-'){
						ungetch('-');
						break;
					}
				}
				ungetch(' ');
				state &= findCentral(&buf[start+1],end-start-2); 
			}
			return 0;//means the input is not right
		ufRecursion:
			state &= findCentral (&buf[i], size-i-1); 
			state &= findCentral (buf, i);

	        }else { // no ()
			i = 0;
			while (i < size){
				c = buf[i++]; 
				if ((c == '+' 
				     || c == '-') && hasNum(buf, 0, i-1)!=-1 ){
					ungetch(c);
					ungetch(' ');
					goto nRecursion;
				}
			}
			i = 0;
			while (i < size){
				c = buf[i++];
				if (c == '*' || c == '/' && hasNum(buf, 0, i-1)!=-1){
					ungetch(c);
					ungetch(' ');
					goto nRecursion;
				}
			}
			return 0;//means input is not right
		nRecursion:
			state &= findCentral (&buf[i], size-i-1); 
			state &= findCentral (buf, i);//the total size become size-1 for a central operator is pushed in stack

		}
		return 1;
	default:
		return 0;
	}

}


int getNormal() {
	size_t maxL = 100;
	char *temBuf;
	size_t len;
        len = getline(&temBuf, &maxL , stdin);//how would getline behave if input is '1+2 EOF \n' --it seems EOF was ignored
	if (len == -1) {
		return EOF;
	}else {
		//make it reverse order
		state = compileReg();
		ungetch('\n');
		state &= findCentral(temBuf, len-1);//len-1 mean not including the '\n'
		regfree(&operand);
		regfree(&conParen);
		if (state){
			return '$';//flush the stack of previous input
		}
		return buf[--bufp];
	}
}
