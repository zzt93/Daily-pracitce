#include <iostream>
#include "my_string.hpp"

using namespace std;

char& my_string::operator [](int i){
    if (i >= 0 || i < size()) {
        return s[i];
    } else {
        throw exception();
    }
    //    return (*this)[i];
}
char my_string::operator[] (int i) const {
    if (i >= 0 || i < size()) {
        return s[i];
    } else {
        throw exception();
    }
}

my_string my_string::operator + (my_string t){
    my_string res(size()+t.size()-1);
    strncpy(res.s, s, size()-1);
    strncpy(&res[size()-1], t.s, t.size());
    return res;
}

//TODO fail at "abc"-"c"
my_string my_string::operator - (my_string t){
    my_string res(size());

    int i = 0;
    int in_res = 0;
    for (; i < size(); ++i ) {
        bool del = false;
        int j = 0;
        for (; j < t.size(); ++j){
            if (s[i] == t[j] && t[i] != '\0'){
                del = true;
                break;
            }
        }
        if(!del){
            res[in_res++] = s[i];
        }
    }
    res.set_size(in_res);
    return res;

}
/*
  1.This function should receive and receive ostream as
  reference for you can't copy constrcut a stream object
  2. cout << a+b
  a+b return a temporary my_string, it have to be a const or
  we may use it as lvalue, which is totally wrong
*/
ostream& operator << (ostream& out, const my_string& t){
    return out << t.s << " size: " << t.size() << endl;
}

my_string& my_string::operator = (my_string str){
    if (this == &str){
        return *this;
    }
    if (str.size() > size()) {
        delete[] s;
        set_size(str.size());
        s = new char[size()];
        strncpy(s, str.s, size());
    } else {
        set_size(str.size());
        strncpy(s, str.s, size());
    }
    return *this;
}

bool my_string::reset(char *str){
    unsigned int len = strlen(str)+1;
    if (len <= size()) {
    } else {
        delete[] s;
        s = new char[len];
    }
    set_size(len);
    strncpy(s, str, length);
}

istream& operator>>(istream& in, my_string& t){
    in.get(t.s, t.size());
    return in;
}

my_string& my_string::operator+=(my_string& t){
}

int main(int argc, char *argv[])
{
    my_string a("abc");
    const char * temp = a.to_cstring();
    cout << temp << endl;
    cout << a;
    my_string c("c");
    a = a+c;
    cout << a;
    a = a-c;
    cout << a;
    cout << (a+c);
    a.reset(".....");
    cout << a;
    cin >> a;
    cout << a;
    return 0;
}

