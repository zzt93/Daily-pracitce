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

/*
A function template, member function of a class template, variable template, or static data member of a class template
shall be defined in every translation unit in which it is implicitly instantiated (14.7.1)
unless the corresponding specialization is explicitly instantiated (14.7.2) in some translation unit
*/

/*
As applied to your case, this means that when the explicit instantiation of templ is removed, the compiler can either

    generate an instantiation normally because of the implicit instantiation in the same TU; or
    inline the call to templ and not generate any externally usable entity.
*/
