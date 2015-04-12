#include <iostream>
using std::string;
using std::cout;
using std::endl;

int main()
{
    string a("abc");
    cout << a.size() << endl;
    a.erase(1);
    cout << a << "size " << a.size() << endl;
    return 0;
}
