#include <iostream>
#include <cmath>

using std::endl;
using std::cout;

int extract(int num, int i) {
	return (int)(num / pow(10, i)) % 10;
}

bool is_palin(int x) {
	if (x < 0) {
		return false;
	}

	int len = 0;
	int tmp = x;
	while(tmp) {
		tmp /= 10;
		len++;
	}

	int l = len / 2;
	for(int i = 0; i < l; i++) {
		int right = extract(x, i);
		int left = extract(x, len - i - 1);
		if (right != left) {
			return false;
		}
	}
	return true;
}

int main(int argc, char *argv[]){
	cout <<	is_palin(1021) << " "
	<< is_palin(123) << " "
	<< is_palin(11) << " "
	<< is_palin(1) << endl;
	return 0;
}
