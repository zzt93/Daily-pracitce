#include <iostream>

using std::cout;
using std::endl;

//extern double max(double, double);
extern void use_max();

int main(int argc, char *argv[])
{
    use_max();
    //    cout << max (1., 2.1);
    return 0;
}
