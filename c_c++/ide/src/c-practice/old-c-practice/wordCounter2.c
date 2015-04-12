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
#define MAXWORD (char *, int);

int getWord (char *, int);
struct key *binsearch (char *, struct key *, int);

int main (int argc, char *argv[]) {
  char word[MAXWORD];
  struct key *p;

  while (getWord(word, MAXWORD) != EOF) {
    if ( isalpha(word[0]){
	if (p =  binsearch（word, keytab, NEYS) != NULL ){
	  P->count ++;
	}
    }
  }
    for ( p=keytab ; p < keytab + NKEYS; p++ ) {
      if ( p -> count > 0 ) {
	printf("%4d %s\n", p->count, p->word);
      }
    }
    return 0;
}

  struct key *binsearch (char *word, struct key *tab, int n) {
    int cond;
    struct key *low = tab;
    struct key *high = &tab[n];
    struct key *mid ;

    while (low < high) {
      mid = low + (high - low)/2;
      if ((cond = strcmp(word, mid->word)) < 0) {
	high = mid - 1;
      }else if ( cond > 0 ) {
	low = mid + 1;
      }else {
	return mid;
      }
    }
    return NULL;
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
