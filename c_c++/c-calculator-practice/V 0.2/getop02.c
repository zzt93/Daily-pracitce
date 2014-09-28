#include <stdio.h>
#include <ctype.h>
#include "calc02.h"


int getop (char s[]){
	int c, tem;
	s[0] = '0';
	while ((s[1] = c = getch()) == ' ' ||c == '\t')
		;
	s[2] = '\0';
	
	//check for -: negative number
	if (c == '-' && isspace(tem=getch()) ){
		ungetch(tem);
		return c;
	}else if (c == '-' && isdigit(tem) ){
		s[1] =  c = tem;
		s[0] = '-';
	}
	
	//return external function name
	if (isalpha(c)){
		int i = 0;
		s[0] = c;
		while (isalpha(s[++i]=c=getch()) );
		s[i] = '\0';
		if (c != EOF)
		{
			ungetch (c);
		}
		return IDENTIFIER;
	}
	//return operator and special character (\n, EOF, $,...)
	if (!isdigit(c) && c != '.')
	{
		return c;
	}
	
	accumulateN (c, s);
	
	return NUMBER;
}

void accumulateN (char c, char s[]) {
	int i = 1;
	if (isdigit (c)){
		while (isdigit (s[++i] = c = getch())){//from right to left
		}
	}
	if (c == '.'){
		while (isdigit (s[++i] = c = getch())){
		}
	}
	s[i] = '\0';
	if (c != EOF)
	{
		ungetch (c);
	}
}
