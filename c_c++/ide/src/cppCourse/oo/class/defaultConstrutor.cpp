#include <iostream>

class StudentID {
    int id;

public:
    int getID(){
        return id;
    }
    
    StudentID(int i){
        id = i;
        std::cout << id << std::endl;
    }
};

class Student {
    StudentID id;
    char * name ;

public:
    int getID(){
        return id.getID();
    }
    Student (char *n, int i):id(StudentID (i)){
        name = n;
        this->id = StudentID (i*2);
    }
};

int main()
{
    Student a("hcw", 1);
    std::cout << a.getID() << std::endl;
    return 0;
}
