#include <cstring>
#include <iostream>

class my_string {
private:
    static const unsigned int END = -1;
    //    unsigned int real_len;
    unsigned int length;
    char *s;
    void set_null_size(unsigned int i){
        length = i;
    }
    /*
    void set_real_len(int i){
        real_len = i;
    }
    unsigned int get_real_len(){
        return real_len;
    }
    */
public:
    my_string(const int i){
        length = i;
        s = new char[length];
    }
    my_string():length(1), s(new char[length]){
        char temp[10] = {0};
        strncpy(s, temp, 10);
    }
    //the char must be const for c_str return the const
    my_string(const char *p){
        length = strlen(p)+1;
        s = new char[length];
        strncpy(s, p, length);
    }
    my_string(std::string& s):my_string(s.c_str()){
    }
    my_string(const my_string& str){
        length = str.length;
        s = new char[length];
        strncpy(s, str.s, length);
    }
    virtual ~my_string(){
        delete[] s;
    }

    const char * to_cstring() const{
        return s;
    }

    unsigned int size() const{
        return length-1;
    }
    unsigned int null_termi_size() const {
        return length;
    }
    my_string operator+ (my_string temp);
    my_string operator- (my_string temp);

    my_string& operator= (my_string temp);

    my_string& operator+=(my_string& temp);
    my_string& operator-=(const my_string& temp);
    
    bool operator < (const my_string& temp){
        int i = strcmp(to_cstring(), temp.to_cstring());
        if (i < 0) {
            return true;
        } else if (i >= 0){
            return false;
        }

    }
    bool operator > (const my_string& temp){
        int i = strcmp(to_cstring(), temp.to_cstring());
        if (i <= 0) {
            return false;
        } else if (i > 0){
            return true;
        }
    }
    bool operator <= (const my_string& temp){
        return !(*this > temp);
    }
    bool operator >= (const my_string& temp){
        return !(*this < temp);
    }
    
    char& operator[] (unsigned int i);
    char operator[] (unsigned int i) const;

    //TODO implement it
    unsigned int copy(char *s, unsigned int len, unsigned int pos=0);
    my_string& erase(unsigned int s = 0, unsigned int count = END);
    bool empty(){
        return length == 0;
    }
    unsigned int find(const std::string& str, unsigned int po=0);
    unsigned int find(const char *s, unsigned int po=0);
    unsigned int find(const char *s, unsigned int po, unsigned int n);
    unsigned int find(const char c, unsigned int po=0);
    friend std::ostream& operator<<(std::ostream& out, const my_string& temp);
    friend std::istream& operator>>(std::istream& in, my_string& temp);
    bool reset(char *s);
};