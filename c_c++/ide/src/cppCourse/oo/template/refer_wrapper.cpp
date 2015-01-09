#include <iostream>
#include <functional>
#include <vector>

using std::vector;
using std::cout;
using std::endl;
using std::reference_wrapper;

template <class T>
class temp {
    
};

int main(int argc, char *argv[])
{
    vector< reference_wrapper<temp<int> > > v;
    int i = 1;
    temp<int> a;
    v.push_back(a);
    
    return 0;
}
