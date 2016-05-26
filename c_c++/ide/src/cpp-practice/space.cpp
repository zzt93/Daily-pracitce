#include <iostream>

using std::endl;
using std::cout;


void printPrimitive() {
	cout << "sizeof(char)=" << sizeof(char) << " ";
	cout << "sizeof(short)=" << sizeof(short) << " ";
	cout << "sizeof(int)=" << sizeof(int) << " ";
	cout << "sizeof(float)=" << sizeof(float) << " ";
	cout << "sizeof(double)=" << sizeof(double) << " ";
	cout << "sizeof(long)=" << sizeof(long) << " ";
	cout << "sizeof(char *)=" << sizeof(char *) << " ";
	cout << endl;
}

struct structc { char c; };
struct structic { int i; char c; };
struct structip { int i; structip *p; };
struct structdc { double d; char c; };
struct structc12 { char c[12]; };

#define MEASURE(T, text) {\
	cout << text << "\t";\
	cout << sizeof(T) << "\t";\
	long lastp = 0;\
	for (int i = 0; i < 11; i++) {\
		T *p = new T;\
		long thisp = (long)p;\
		if (lastp != 0) {\
			cout << " " << thisp - lastp;\
		}\
		lastp = thisp;\
	}\
	cout << "\n";\
}\

void printStruct() {
	cout << "STRCTURE\tsizeof\tnew size\n"; 
	MEASURE(structc, "structc");
	MEASURE(structic, "structic");
	MEASURE(structip, "structip");
	MEASURE(structdc, "structdc");
	MEASURE(structc12, "structc12");
}

int main(int argc, char *argv[]){
	printPrimitive();
	printStruct();
	return 0;
}
