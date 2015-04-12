//answer : page 142
//2014.6.11
//zzt

#include <stdio.h>
#include <string.h>

void expand(char* s1, char* s2);

int main(int argc, char const *argv[])
{
	char *string1[] = { "a-z-", "z-a-", "-1-6-", 
 						"a-ee-a", "A-R-L", "1-9-1", 
 						"5-5","a-z0-9", NULL};
	char string2[100];
	int i;

	for (i = 0; string1[i]; ++i)
	{
		printf("%s\n", string1[i]);
		expand(string1[i] , string2);
		printf("%s\n", string2);
	}
	return 0;
}

void expand(char* s1, char* s2) {
	int i = 0;
	int j = 0;
	int i1, i2, i3;
	int counterFor2 = 0;
	int parts[100];


	for (; s1[i]=='-'; ++i) {
		s2[counterFor2++] = '-';
	}
	
	//split s1 into several parts
	for (; s1[i] != '\0'; ++i)
	{
		if (s1[i]=='-' && s1[i+1]!='\0')
		{
			parts[j++] = i;
		}
	}

	for (i1 = 0; i1 < j; ++i1)
	{
		if (s1[parts[i1]-1] <= s1[parts[i1]+1])
		{
			for (i2 = 0; s1[parts[i1]-1]+i2 <= s1[parts[i1]+1]; ++i2)
			{
				s2[counterFor2++] = s1[parts[i1]-1] + i2;
			}
		}else {
			for (i3 = 0; s1[parts[i1]-1]-i3 >= s1[parts[i1]+1]; ++i3)
			{
				s2[counterFor2++] = s1[parts[i1]-1] - i3;
			}
		}
	}
	
	if (s1[i]=='\0' && s1[i-1]=='-') {
		s2[counterFor2++] = '-';
	}
	s2[counterFor2] = '\0';
}
