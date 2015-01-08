#include <iostream>
#include <string>
#include <vector>
#include "graph.hpp"

using std::cout;
using std::endl;
using std::vector;
using std::string;

int main()
{
    Vertex<int, string> v1(1, "1");
    Vertex<int, string> v2(2, "2");
    Vertex<int, string> v3(3, "3");
    Vertex<int, string> v4(4, "4");
    Edge<int, string> e12(v1, v2);
    Edge<int, string> e13(v1, v3);
    Edge<int, string> e23(v2, v3);
    Edge<int, string> e24(v2, v4);
    Edge<int, string> e34(v3, v4);
    return 0;
}
