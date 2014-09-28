#include <stdio.h>
#include <ctype.h>
#include <string.h>

struct key {
  char *word;
  int count;
} keytab[] = {
  "auto", 0,
  "break", 0,
  "case", 0,
  "char", 0,
  "const", 0,
  "continue", 0,
  /*...*/
  "unsigned", 0,
  "void", 0,
  "volatile",0,
  "while", 0
};

#define NKEYS (sizeof keytab / sizeof keytab[0] )
#define MAXWORD 100

int getword (char *, int );
int binsearch (char *, strcut key *, int);

int main (int argc, char *argv[] ) {
  int n;
  char word[MAXWORD];

  while (getword (word, MAXWORD) != EOF) {
    if (isalpha(word[0])) {
      if ( ( n = binsearch (word, keytab, NKEYS) ) >= 0 ) {
	keytab[n].count ++;
      }
    }
  }
  for ( n = 0; n < NKEYS; ++n ) {
    if (keytab[n].count > 0 ) {
      printf("%4d %s\n", keytab[n].count, keytab[n].word);
    }
  }
  return 0;
}

int binsearch (char *word, struct key tab[], int n) {
	int cond;
	int low, high, mid;

	low = 0;
	high = n - 1;
	while ( low < high ) {
		mid = (low + high)/2;
		if ( (cond = strcmp(tab[mid].word, word)) < 0) {
			low = mid + 1;
		}else if (cond > 0) {
			hign = mid - 1;
		}else {
			return mid;
		}
	}
	return -1;
}

int getWord (char *word, int lim) {
	int c, getch(void);
	void ungetch(int);
	char *w = word;

	while (isspace(c = getch()));
	if (c != EOF) {
		*w++;
	}
	if (!isalpha(c)) {
		*w = '\0';
		return c;
	}
	for (; --lim > 0; w++) {
		if (!isalnum(*w = getch())) {
			ungetch(*w);
			break;
		}
	}
	*w = '\0';
	return word[0];
}
#define BUFSIZE 1000
char buf[BUFSIZE] ;
int index;
int getch () {
	char c;
	if (index > 0) {
		return buf[--index];
	}else  {
		return getchar();
	}
}
void ungetch(int c) {
	if (index < BUFSIZE) {
		buf[index++] = c;
	}else {
		printf("it is full");
	}
}
