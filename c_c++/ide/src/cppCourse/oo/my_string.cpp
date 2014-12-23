#include <iostream>
#include "my_string.hpp"

using namespace std;

char& my_string::operator [](unsigned int i){
    if (i >= 0 || i < size()) {
        return s[i];
    } else {
        throw exception();
    }
    //    return (*this)[i];
}
char my_string::operator[] (unsigned int i) const {
    if (i >= 0 || i < size()) {
        return s[i];
    } else {
        throw exception();
    }
}

my_string my_string::operator + (my_string t){
    my_string res(null_termi_size()+t.null_termi_size()-1);
    strncpy(res.s, s, null_termi_size()-1);
    strncpy(&res[null_termi_size()-1], t.s, t.null_termi_size());
    return res;
}

my_string my_string::operator - (my_string t){
    my_string res(null_termi_size());

    unsigned int i = 0;
    int in_res = 0;
    for (; i < null_termi_size(); ++i ) {
        bool del = false;
        unsigned int j = 0;
        for (; j < t.null_termi_size(); ++j){
            if (s[i] == t[j] && t[j] != '\0'){
                del = true;
                break;
            }
        }
        if(!del){
            res[in_res++] = s[i];
        }
    }
    res.set_null_size(in_res);
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
    return out << "\'" << t.s << "\'"<< " size: " << t.size() << endl;
}

my_string& my_string::operator = (my_string str){
    if (this == &str){
        return *this;
    }
    if (str.null_termi_size() > null_termi_size()) {
        delete[] s;
        set_null_size(str.null_termi_size());
        s = new char[null_termi_size()];
        strncpy(s, str.s, null_termi_size());
    } else {
        set_null_size(str.null_termi_size());
        strncpy(s, str.s, null_termi_size());
    }
    return *this;
}

bool my_string::reset(char *str){
    unsigned int len = strlen(str)+1;
    if (len <= null_termi_size()) {
    } else {
        delete[] s;
        s = new char[len];
    }
    set_null_size(len);
    strncpy(s, str, length);
    return true;
}

istream& operator>>(istream& in, my_string& t){
    in.get(t.s, t.size());
    return in;
}

my_string& my_string::operator+=(my_string& t){
    *this = *this + t;
    return *this;
}
my_string& my_string::operator-=(const my_string& t){
    *this = *this - t;
    return *this;
}

/*
  The copied c_string doesn't contain a null character
*/
unsigned int my_string::copy(char *s, unsigned int len, unsigned int pos){
    if (pos > size()){
        return 0;
    }
    if (pos + len > size()){
        len = size()- pos;
    }
    strncpy(s, &(this->to_cstring())[pos], len);
    return len;
}

my_string& my_string::erase(unsigned int start, unsigned int count){
    if(count == END || count > size()){
        count = size();
    }
    if (start > size()-1){
        return *this;
    }
    if (start + count > size()){
        count = size()-start;
    }
    if (start + 2*count <= size()) {//means that the string can be copied by itself
        for (unsigned int i = 0; i < size()-start; ++i) {
            s[start+i] = s[start+i+count];
        }
        set_null_size(size()-count+1);
    } else {
        char *s1 = new char[size()-count+1];
        strncpy(s1, s, start);
        strncpy(&s1[start], &s[start+count], size()+1-count-start);
        this->reset(s1);
    }
    return *this;
}

unsigned int my_string::find(const string& str, unsigned int po){
    const char *s1 = str.c_str();
    unsigned int t_size = str.size();
    for (unsigned int j = po; j < size(); ++j) {
        if (s1[0] == s[j]){
            unsigned int i = 1;
            for (; i < t_size; ++i){
                if (s[j+i] != s1[i]){
                    break;
                }
            }
            if (i == t_size){
                return j;
            }
        }
    }
    return END;
}

/*
  The pointer to a string is expected to be terminated by a null char
*/
unsigned int my_string::find(const char *s1, unsigned int po){
    return find(string(s1), po);
}

/*
  n: the length of sequence of s1
*/
unsigned int my_string::find(const char *s1, unsigned int po, unsigned int n){
    return find(string(s1, s1+n), po);
}

unsigned int my_string::find(const char c, unsigned int po){
    return find(string(1, c), po);
}


int main(int argc, char *argv[])
{
    string s1;
    string s2;
    if (argc >= 3) {
        s1 = argv[1];
        s2 = argv[2];
    } else {
        s1 = "abcadfga";
        s2 = "bac";
    }

    my_string a(s1);
    my_string c(s2);
    
 /*   const char * temp = a.to_cstring();
    cout << "original cstring and my_string:" << endl;
    cout << temp << endl;
    cout << a;
    cout << "After a += c(c:" << c.to_cstring() << ")" << endl;
    a += c;
    cout << a;
    cout << "After a -= c(c:" << c.to_cstring() << ")" << endl;
    a -= c;
    cout << a;
    cout << "After a += c(c:" << c.to_cstring() << ")" << endl;
    cout << (a+c);
    a.reset(".....");
    cout << a;
    cout << "Read into a:" << endl;
    cin >> a;
    cout << a;
    //test erase
    a.erase(0);
    cout << a << endl;
    a.reset("asbc");
    a.erase(1, 3);
    cout << a << endl;
    a.reset("asbc");
    a.erase(1, 1);
    cout << a << endl;
    a.reset("asbc");
    a.erase(1, 100);
    cout << a << endl;
    a.reset("asbc");
    a.erase(5);
    cout << a << endl;
    char copy[] = "gggggggggggggg";
    a.copy(copy, 300, 1);
    cout << a;
    cout << copy << endl ;
    cout << a.find("b");
    cout << a.find('b');
    cout << a.find("ba", 0, 1);
    cout << a.find(string("b"));
    */
    return 0;
}

