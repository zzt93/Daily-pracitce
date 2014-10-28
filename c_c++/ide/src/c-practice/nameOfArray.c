#include <stdio.h>

int main()
{
	int a[4][3] = {10,20,30,40,50,60,70,80,90,100,110,120};
	// a == &a[0]  a[0] == &a[0][0]
	/*--a decays to pointer to first element (first row) of array and is of type int (*)[3].
      --*a dereference the row pointed by a and further decayed to pointer to first element of first row. It is of type int *.
      a[0] is representing the first row which is of type int [3].
      In expression it decays to pointer to first element of first row and is of type int * after decay. */
	printf("%d\n", a == &a[0]);
	printf("%d\n", a[0] == &a[0][0]);
	printf("%d",((a==*a) && (*a==a[0])));
	
	return 0;
}
