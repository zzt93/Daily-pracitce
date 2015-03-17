#include <iostream>

using std::cout;
using std::endl;

int main(int argc, char *argv[])
{
    int a = -10000;
    long b =  -99999887310;
    long c = a - b;
    cout << c << " " << a - b << endl;
    return 0;
}
