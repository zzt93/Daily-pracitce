#include <cstring>

class my_string {
private:
    unsigned int real_len;
    unsigned int length;
    char *s;
    void set_size(int i){
        length = i;
    }

    void set_real_len(int i){
        real_len = i;
    }
    unsigned int get_real_len(){
        return real_len;
    }

public:
    my_string(int i){
        length = i;
        s = new char[length];
    }
    my_string():length(1), s(new char[length]){
        char temp[10] = {0};
        strncpy(s, temp, 10);
    }
    my_string(char *p){
        length = strlen(p)+1;
        s = new char[length];
        strncpy(s, p, length);
    }
    my_string(const my_string& str){
        length = str.length;
        s = new char[length];
        strncpy(s, str.s, length);
   }
    virtual ~my_string(){
        delete[] s;
    }

    const char * to_cstring(){
        return s;
    }

    unsigned int size() const{
        return length;
    }
    void set_size(unsigned int i){
        length = i;
    }
    my_string operator+ (my_string temp);
    my_string operator- (my_string temp);

    my_string& operator= (my_string temp);

    my_string& operator+=(my_string& temp);
    
    char& operator[] (int i);
    char operator[] (int i) const;

    friend std::ostream& operator<<(std::ostream& out, const my_string& temp);
    friend std::istream& operator>>(std::istream& in, my_string& temp);
    bool reset(char *s);
};