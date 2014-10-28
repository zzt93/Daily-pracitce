#include <stdio.h>

int main(int argc, char const *argv[])
{
	union tag {
		int ival;
		char a;
		double dval;
	} u[10] = {
		0,
	1,
	2,
	3,
	4,
	5,
	6.1,    //it seems that a cast happen here
	7,
	8,
	9,
	};//only the first member can be used to initialize ?

	printf("%d\n", u[0].ival);
	printf("%c\n", u[1].ival);
	
	u[0].a = 'a';
	printf("%c\n", u[0].a);      //a
	printf("%d\n", u[0].ival);  //97--'a'
	printf("%c\n", u[0]);		//a
	printf("%d\n", u[1]);		//1
	printf("%d\n", u[6]);		//6 for %d and 0.000 for %f (show it is not a double number)
	
	printf("%d\n", sizeof u);   //80 --> sizeof (double) * 10 ?
	return 0;
}
