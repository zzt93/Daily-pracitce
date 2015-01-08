#include <iostream>

inline int factorial (int n){
    if (n == 1) {
        return 1;
    } else {
        return n*factorial(n-1);
    }
}

int main(int argc, char *argv[])
{
    int i =  factorial(3);
    std::cout << i << std::endl;
    return 0;
}
