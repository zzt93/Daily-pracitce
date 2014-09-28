#include <vector>
#include <string>

using namespace std;

typedef string::size_type ssize;

vector<string> pali (string str) {
	vector<string> res;
	ssize size = str.size();
	vector<ssize> end;
	ssize i;

	for ( i = 0; i < size-1; ++i) {
		char temp = str[i];
		if(find (str, temp, i, end)) {
			for(int j=0; j<end.size(); ++j){
				ssize tem = end.get(j);
				if(isPa( str.substr(i, tem-i) )) {
					res.push_back(str.substr(i, tem-i));
				}
			}
		}
	}
}

bool isPa (string str) {
	ssize temp = str.size();
	if(temp == 0 || temp == 1) {
		return true;
	}else {
		if(str[0] == str[temp-1]){
			isPa(str.substr(1, temp-2));
		}else {
			return false;
		}
	}
}

bool find (string str, char aim, ssize start, vector<ssize>& end) {
	for(ssize i = 0; i < str.size(); ++i) {
		if (str[i] == aim){
			end.push_back(i);
		}
	}
}
