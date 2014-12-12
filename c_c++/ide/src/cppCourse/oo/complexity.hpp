
class Complexity{
private:
    double first;
    double second;

public:
    Complexity(){
        first = 0;
        second = 0;
    }
    Complexity(double first, double second=0){
        this->first = first;
        this->second = second;
    }
    Complexity(const Complexity& t){
        first = t.first;
        second = t.second;
    }

    virtual ~Complexity(){
    }

    double getF(){
        return first;
    }
    double getS(){
        return second;
    }
    void set(double f, double s){
        second = s;
        first = f;
    }
    friend std::ostream& operator << (std::ostream& out, const Complexity t){
        out << t.first << "+" << t.second << "i" << std::endl;
    }
    Complexity operator+ (Complexity&);
    Complexity operator- (Complexity&);
    Complexity operator* (Complexity&);
    Complexity operator/ (Complexity&);
    Complexity& operator= (Complexity&);
};