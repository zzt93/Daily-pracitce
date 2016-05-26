#include <stdio.h>
#include <wchar.h>
#include <locale.h>

int main(int argc, char *argv[]){
	// make print %ls work
	setlocale(LC_ALL, "");
    puts("我是");
	printf("我是");
	printf("%ls", L"我是");
	return 0;
}
