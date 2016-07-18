#include <iostream>
#include <string>
#include <sstream>
#include <cstdlib>

using namespace std;

int reverseInt(int i) {
	bool negative = false;
	if (i < 0) {
		negative = true;
		i = -i;
	}
	stringstream ss;
	ss << i;
	string s = ss.str();
	string::iterator it = s.begin();
	string::reverse_iterator rit = s.rbegin();
	for(; it < rit.base(); it++, rit++) {
		char tmp = *it;
		*it = *rit;
		*rit = tmp;
	}
	long tmp = atol(s.c_str());
	long msb = tmp >> 31;
	int res;	
	if(msb == 0 || msb == -1) {
		res = (int)tmp;
	} else {		
		res = 0;
	}
	//cout << s << endl;
	if (negative) {
		res = -res;
	}
	return res;
}


int main(int argc, char *argv[]){
	cout << reverseInt(100) << " " <<
	reverseInt(-123) << " " <<
	reverseInt(1000000003) << " " << 
	reverseInt(1234) << endl;
	
	return 0;
}
