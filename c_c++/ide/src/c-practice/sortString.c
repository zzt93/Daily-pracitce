#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAXLINES 500

char *lineptr[MAXLINES];

int readlines(char *lineptr[], int maxline);
void writelines(char *lineptr[], int nlines);

void qisort(char *lineptr[], int left, int right);

int main(int argc, char const *argv[])
{
	int nlines;

	if ((nlines = readlines(lineptr, MAXLINES)) >= 0)
	{
		qisort(lineptr, 0, nlines-1);
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

void qisort (char *v[], int left, int right) {
	int i, last;
	void swap (char *v[], int i, int j);

	if (left >= right)
	{
		return;
	}
	swap(v, left, (left + right)/2);
	last = left;
	for (i = left+1 ; i <= right; ++i)
	{
		if (strcmp(v[i], v[left]) < 0)
		{
			swap(v, i, ++last);
		}
	}
	swap(v, left, last);
	qisort(v, left, last-1);
	qisort(v, last+1, right);
}

void swap (char *v[], int i, int j) {
	char *temp;

	temp = v[i];
	v[i] = v[j];
	v[j] = temp;
}
