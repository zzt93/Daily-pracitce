#include <stdio.h>

int main(int argc, char *argv[]) {
	signed char cresult, c1, c2, c3;

        unsigned char a = 0xff;  //-->(int)(unsigned char)0xff
        char b = 0xff;		 //-->(int)(char)0xff
        int c = a==b; //  false   -->happen here
        printf("C: %d\n",c);

	c1 = 100;
	c2 = 3;
	c3 = 4;
	/*The two/* int values are added, and the sum is truncated to  fit into the char type*/
	cresult = c1 * c2 / c3;
	printf("%d\n",cresult);

	return 0;
}
