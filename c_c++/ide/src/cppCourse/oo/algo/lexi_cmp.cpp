#include <iostream>
#include <algorithm>
#include <cctype>

using std::cout;
using std::endl;
using std::lexicographical_compare;

bool my_cmp(char& t1, char& t2){
    
}
int main(int argc, char *argv[])
{
    char foo[] = "APPLE";
    char bar[] = "applet";

    std::cout << std::boolalpha;
    lexicographical_compare(foo, foo+5, bar, bar+6);
    return 0;
}
