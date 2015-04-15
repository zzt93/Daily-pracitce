#include <iostream>
#include <string>
#include "utility/console_reader.cpp"
#include "utility/input_handler.hpp"

using std::endl;
using std::cout;
using std::cerr;

int main(int argc, char *argv[]){
    Console_reader r;
    r.skip_line(379 + 1);
    cout << "unsigned char head_bmp[] = {\n";
    for (unsigned int i = 0; i < 21; ++i) {
        int t = 0;
        for (unsigned int i = 0; i < 1000; ++i) {
            r.next_hex(t);
            r.skip_char(1, ',');
        }
        int j = 0;
        while (r.next_hex(t) && j < 23) {
            r.skip_char(1, ',');
            cout << t << ", ";
            j++;
        }
        cout << endl;
        r.skip_line(1);
    }
    cout << "};";
	return 0;
}
