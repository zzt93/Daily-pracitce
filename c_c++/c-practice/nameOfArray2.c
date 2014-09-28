#include <stdio.h>
int main()
{
    int (*p)[3];//--a pointer to an array of 3 integer
                //--brackets [] has higher precedence than *
                //different from  *p[3]--array of three pointers

    int a[3] = { 5, 4, 6 };

    p = &a;
    //p = a; [Warning] assignment from incompatible pointer type [enabled by default]
    /*
    Array objects have no padding between elements (before or after)
    so the address of the array and the address of the first element of the array are the same. */
    printf("&a:%d\ta:%d\n",&a,a);
    //   a == &a[0]    in general, the address of the first byte is said to be the address of the variable. 
    //(void *) a == (void *) &a
    
    /* a+1 -- &a[0+1] -- address of the second element of the array
      &a+1 -- &a[length-1 + 1] -- address one past the last element of the array */
    printf("a+1:%d\t&a+1:%d\n",a+1, &a+1 );
    printf("%d\n",*(*p + 1));
    printf("%d",*(p+1));
}
