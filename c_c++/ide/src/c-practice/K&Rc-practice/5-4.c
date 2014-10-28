#include <stdio.h>

int strend(char *p1, char *p2);

int main(int argc, char const *argv[])
{
	char str1[10] = "asd fasd";
	char str2[10] = "fasd";
	printf("%d\n", strend(str1, str2));
	return 0;
}

int strend(char *p1, char *p2) {
	int length = 0;
	while (*p1++);
	while (*p2++) {
		++length;
	}
	length += 3;
	while (--length) {
	//	printf("%d\n%d\n", *p1, *p2);
		if ((*p1--) != (*p2--))
		{
			break;
		}
	}
	if (length)
	{
		return 0;
	}
	return 1;

}
