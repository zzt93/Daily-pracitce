#include <iostream>

using std::cout;
using std::endl;

template <class T>
T max_zzt(T t1, T t2){
    return (t1>t2)?t1:t2;
}

/*
int main(int argc, char *argv[])
{
    cout << max(11., 2.1);//1, 2.1 error:deduction failed
    return 0;
}
*/
void use_max(){
    cout << max_zzt(1.1, 2.1);
}