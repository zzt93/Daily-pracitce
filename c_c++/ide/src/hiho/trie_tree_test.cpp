#include <iostream>
#include <string>
#include <vector>
#include "utility/console_reader.cpp"

using std::cout;
using std::endl;
using std::vector;
using std::stoi;

int main(int argc, char *argv[])
{
    Console_reader r;
    r.readlines();
    vector<string> lines = r.lines();
    int size = stoi(lines[0]);
    int times = stoi(lines[size+1]);
    for (unsigned int i = 0; i < size; ++i) {
        //cout << lines[i] << endl;
        
    }
    
    return 0;
}
