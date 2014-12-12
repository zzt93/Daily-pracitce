#include <iostream>
#include "complexity.hpp"

Complexity Complexity::operator + (Complexity& t){
    Complexity res(*this);
    double f = t.getF()+getF();
    double s = t.getS()+getS();
    res.set(f, s);
    return res;
}
Complexity Complexity::operator - (Complexity& t){
    Complexity res(*this);
    double f = getF()-t.getF();
    double s = getS()-t.getS();
    res.set(f, s);
    return res;
}
Complexity Complexity::operator * (Complexity& t){
    Complexity res(*this);
    double f = getF()*t.getF() - getS()*t.getS();
    double s = getF()*t.getS() + getF()*t.getS();
    res.set(f, s);
    return res;
}
Complexity Complexity::operator / (Complexity& t){
    Complexity res(*this);
    t.set(t.getF(), -t.getS());
    Complexity comp(t);
    res*t;//TODO
}
int main(int argc, char *argv[])
{
    Complexity a(1, 2);
    Complexity b(2, 3);
    std::cout << a+b;
    return 0;
}
