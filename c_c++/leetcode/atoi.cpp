#include <iostream>
#include <string>
#include <climits>
#include <cstdlib>

using namespace std;

bool validChar(char c) {
	return c >= '0' && c <= '9';
}

int myAtoi(string str) {
	const char *s = str.c_str();
	// skip space
	while(isspace(*s)) {
		s++;
	}

	int mul = 1;
	if (*s == '-') {
		mul = -1;
		s++;
	} else if (*s == '+') {
		mul = 1;
		s++;
	}
	
	long res = 0;
	int loopCount = 0;
	while(*s) {
		// check invalid char
		if (!validChar(*s)) {
			break;
		}
		loopCount++;

		res *= 10;
		res += (*s - '0');
		s++;
	}	
	// check overflow
	int msb = res >> 31;
	if (loopCount > 10 || (msb != 0 && msb != -1)) {
		return mul > 0 ? INT_MAX : INT_MIN;
	}
	return res * mul;
}

int main(int argc, char *argv[]){
	cout << myAtoi("234") << " " 
	<< myAtoi("13rf") << " "
	<< myAtoi("  001") << " "
	<< myAtoi("      -11919730356x") << " "
	<< myAtoi("21474836418") << endl;
	return 0;
}
