#include <string>
#include <iostream>

using std::endl;
using std::cout;
using std::string;


string zigzag(string input, int row) {
	if (row == 1) {
		return input;
	}
	int len = input.length();
	string res(len, ' ');
	int two_line_gap = 2 * row - 2;
	int i = 0, o = 0;
	// fill first line
	while(o < len) {
		res[i++] = input[o];
		o += two_line_gap;
	}
	int one_line_gap = 2 * row - 4;
	for(int j = 1; j < row - 1; j++) {
		o = j;
		int count = 0;
		while(o < len) {
			res[i++] = input[o];
			o += (count & 1) == 0 ? one_line_gap : two_line_gap - one_line_gap;
			count ++;
		}
		one_line_gap -= 2;
	}
	// fill last line
	o = row - 1;
	while(o < len) {
		res[i++] = input[o];
		o += two_line_gap;
	}
	return res;
}

int main(int argc, char *argv[]){
	cout << zigzag("PAHNAPLSIIGYIR", 4);
	return 0;
}
