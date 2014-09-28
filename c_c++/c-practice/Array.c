#include <stdio.h>

int main()
{
	int A[100];
	char B[20];
	printf("%p %p\n", A, A+1);
    //puts("上面A与A+1的地址差即为A中一个基本元素的大小");
	printf("%p %p\n", B+0, B+3);

	A[1] = 2; A[2] = 3; B[0] = '1';
	printf("A1:%d A2:%d B0:%C\n", A[1], A[2], B[0]);

	A[101] = 10;
	//puts("注意C的编译类型检查并没有强到会爆出这种错误,事实上这种错误在运行中也未必会被发现");
	A[1000] = 10;
	//这就真正的访问了未被申请过的空间

	return 0;
}
