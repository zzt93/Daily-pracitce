#include <iostream>
#include <vector>
#include <map>

using std::endl;
using std::cout;

using namespace std;

map<char, vector<char>> numbers = {
	{'2', {'a', 'b', 'c'}},
	{'3', {'d', 'e', 'f'}},
	{'4', {'g', 'h', 'i'}},
	{'5', {'j', 'k', 'l'}},
	{'6', {'m', 'n', 'o'}},
	{'7', {'p', 'q', 'r', 's'}},
	{'8', {'t', 'u', 'v'}},
	{'9', {'w', 'x', 'y', 'z'}}
};

void phone(vector<string>& v, const char *digits) {
	if(*digits == '\0') {
		return;
	} 
	if(*digits < '2' || *digits > '9' ) {
		v.clear();
		return;
	}
	vector<char> t = numbers[*digits];
	vector<string> tmp;
	for(string& s: v) {
		for(char& c: t) {		
			string tmpS = s + c;
			tmp.push_back(tmpS);
		}
	}
	v.clear();
	v.insert(v.end(), tmp.begin(), tmp.end());
	phone(v, ++digits);
}

int main(int argc, char *argv[]){
	vector<string> v = {""};
	string s = "312";
    if(s.empty()) {
        return {};
    }
	phone(v, s.c_str());
	for(string& s: v) {
		cout << s << endl;
	}
	return 0;
}
