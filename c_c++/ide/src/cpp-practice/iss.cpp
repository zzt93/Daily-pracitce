#include <string>       // std::string
#include <iostream>     // std::cout
#include <sstream>      // std::istringstream

int main () {
    std::istringstream iss;
    std::string strvalues = "32  240 2 145  10 ";

    iss.str (strvalues);

    //TODO use iss as the condition is wrong
    for (int n=0; iss; n++)
    {
        int val;
        iss >> val;
        std::cout << val << '\n';
    }
    std::cout << "Finished writing the numbers in: ";
    std::cout << iss.str() << '\n';

    //the right way
    std::string str = "32  240 2 145";
    iss.str (str);
    iss.clear();//TODO this clear the flags last iteration make
    int val;
    for (; iss>>val; )
    {
        std::cout << val << '\n';
    }
    std::cout << "Finished writing the numbers in: ";
    std::cout << iss.str() << '\n';
 
    return 0;
}