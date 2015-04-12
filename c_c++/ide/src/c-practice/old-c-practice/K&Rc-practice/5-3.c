#include <stdio.h>

void stringcat (char* p1, char *p2);

int main(int argc, char const *argv[])
{
	char string1[30] = "asd123 ";
	char string2[10] = "fghasdas";
	stringcat(string1, string2);
	printf("%s\n", string1);
	return 0;
}

void stringcat (char *p1, char *p2) {
	while (*p1++);
	--p1;
	while (*p1++ = *p2++);
}
