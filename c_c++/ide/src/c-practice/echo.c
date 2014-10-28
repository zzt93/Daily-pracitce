#include <stdio.h>

int main(int argc, char const *argv[])
{
	int i;

	for (i = 0; i < argc; ++i)
	{
		printf("%s%s\n", argv[i], (i < argc-1) ? " " : "");
	}
	return 0;
}