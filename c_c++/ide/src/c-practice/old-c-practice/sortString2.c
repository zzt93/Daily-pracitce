#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAXLINES 500

char *lineptr[MAXLINES];

int readlines(char *lineptr[], int maxline);
void writelines(char *lineptr[], int nlines);

void qisort(void *lineptr[], int left, int right, int (*comp)(void *, void *));
int numcmp(char *, char *);

int main(int argc, char const *argv[])
{
	int nlines;
	int numeric = 0;

	if (argc > 1 && strcmp(argv[1], "-n") == 0)
	{
		numeric = 1;
	}

	if ((nlines = readlines(lineptr, MAXLINES)) >= 0)
	{
		qisort((void **)lineptr, 0, nlines-1, (int (*)(void*, void*))(numeric ? numcmp :strcmp));
		writelines(lineptr, nlines);
		return 0;
	}else {
		printf("error: input too big  to sort\n");
		return 1;
	}
	return 0;
}

#define MAXLEN 1000
int getlines(char *, int);

int readlines(char *lineptr[], int maxline) {
	int len, nlines;
	char *p, line[MAXLEN];

	nlines = 0;
	while ((len = getlines(line, MAXLEN)) > 0) {
		if (nlines >= maxline || (p = malloc(len)) == NULL)
		{
			return -1;
		}
		else {
			line[len-1] = '\0';
			strcpy(p, line);
			lineptr[nlines++] = p;
		}
	}
	return nlines;
}

void writelines(char *lineptr[], int nlines) {
	int i;

	for (i = 0; i < nlines; ++i)
	{
		printf("%s\n", lineptr[i]);
	}
	
/*	while (nlines-- > 0) {
		printf("%s\n", *lineptr++);
	}*/
}

int getlines (char *s, int lim) {
	int c, i;

	for (i = 0; i < lim-1 && (c=getchar()) != EOF && c != '\n'; ++i)
	{
		s[i] = c;
	}
	if (c == '\n')
	{
		s[i] = c;
		++i;
	}
	s[i] = '\0';
	return i;
}

void qisort (void *v[], int left, int right, int (*comp)(void *, void *) ) {
	int i, last;
	void swap (void *v[], int i, int j);

	if (left >= right)
	{
		return;
	}
	swap(v, left, (left + right)/2);
	last = left;
	for (i = left+1 ; i <= right; ++i)
	{
	  if ((*comp) (v[i], v[left]) < 0 )
		{
			swap(v, i, ++last);
		}
	}
	swap(v, left, last);
	qisort(v, left, last-1, comp);
	qisort(v, last+1, right, comp);
}

void swap (void *v[], int i, int j) {
	void *temp;

	temp = v[i];
	v[i] = v[j];
	v[j] = temp;
}

int numcmp (char *s1, char *s2) {
	double v1, v2;

	v1 = atof(s1);
	v2 = atof(s2);
	if (v1 < v2)
	{
		return -1;
	}else if (v1 > v2){
		return 1;
	}else {
		return 0;
	}
}
