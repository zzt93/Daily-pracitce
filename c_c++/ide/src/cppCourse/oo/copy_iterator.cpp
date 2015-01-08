#include <iostream>
#include "test_class.hpp"

using std::endl;
using std::cout;

int main(int argc, char *argv[])
{
    B b;
    b.add(1);
    b.add(2);
    b.add(3);
    b.restart();

    B b2 = b;
        std::cout << b2.next() << endl;
        std::cout << b.next() << endl;
    return 0;
}

