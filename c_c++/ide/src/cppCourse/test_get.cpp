#include <iostream>
#include <fstream>

using namespace std;

int main(int argc, char *argv[])
{
    /*    char a[10] = {0};
    cin.getline(a, 10);
    cout << a << endl;
    cin.getline(a, 10);
    cout << a << endl;*/

    fstream back("back.txt", ios::in);
    streampos sa = back.tellg();
    back.seekg(0, ios::end);
    streampos e = back.tellg();
    back.seekg(0, ios::beg);
    int a = e - sa;
    cout << a << endl;
    char s[a];
    if (back.getline(s, a)) {
        cout << s << endl;
        cout <<  a << endl;
    }
    back.close();
    return 0;
}
