#include <stdio.h>
#include <string.h>
#include "calc.h"
#define BUFSIZE 100

char buf[BUFSIZE];
int bufp = 0;

int getch (void){
	return (bufp>0) ? buf[--bufp] : getchar();
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

