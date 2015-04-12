#include <iostream>

int using_pre(int i){
    return ++i;
}
int using_post(int i){
    return i++;
}
int main(int argc, char *argv[])
{
    std::cout << using_post(20) << std::endl;
    std::cout << using_pre(20) << std::endl;
    return 0;
}
