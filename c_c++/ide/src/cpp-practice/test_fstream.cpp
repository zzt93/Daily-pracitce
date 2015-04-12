#include <iostream>
#include <fstream>

using namespace std;
int main(int argc, char *argv[])
{
    fstream a("a.txt");
    a.write("asd", 4);
    fstream b("b.txt");
    if (b.is_open()) {
        b << "bb" << endl;
    } else {
        cout << "can't open b" << endl;
    }

    return 0;
}
