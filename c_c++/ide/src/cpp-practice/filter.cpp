#include <iostream>
#include <string>
#include "utility/console_reader.cpp"
#include "utility/input_handler.hpp"

using std::endl;
using std::cout;
using std::cerr;

int main(int argc, char *argv[]){
    Console_reader r;
    string s;
    r.skip_line(375 + 1);
    cout << "unsigned char head_bmp[] = {\n";
    for (unsigned int i = 0; i < 20; ++i) {
        for (unsigned int i = 0; i < 999; ++i) {
            int i = 0;
            r.next_int(i);
        }

        for (unsigned int j = 0; j < 25; ++j) {
            int t = 0;
            cerr << r.next_hex(t);
            cout << t << ", ";
        }
        cout << endl;
        r.skip_line(1);
    }
    cout << "};";
	return 0;
}
