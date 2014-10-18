#include <iostream>
#include <cstdlib>

using namespace std;

/*
  delete every char in s2 that show in s1
*/
int squeeze1(char *s1, const char *s2){
    int count = 0, length = 0;
    while(*s1++ != '\0'){
        length++;
    }
    char *tem = (char *)malloc(sizeof length);
    bool exit = false;
    while (*s1 != '\0') {
        while (*s2 != '\0') {
            if(*s1 == *s2){
                count++;
                exit = true;
                break;
            }
            ++s2;
        }
        if(!exit){
            *tem++ = *s1;
        }
        ++s1;
    }
}

int squeeze2(char *s1, char *s2){
    int count = 0, length = 0;
    while (*s1++ != '\0') {
        ++length;
    }
    int sign[length];
    
}
int find_replace_str(char *s, const char *find, const char *replace){
}
int main(int argc, char *argv[])
{
    if (argc <= 2) {
        cout << "Usage: a.out str str (squeeze)" << endl;
        cout << "Or: a.out str strToFind strToReplace" << endl;
    } else if (argc == 3) {
        squeeze(argv[1], argv[2]);
        cout << argv[1];
    } else if(argc == 4) {
        find_replace_str(argv[1], argv[2], argv[3]);
        cout << argv[1];
    } else {
        cerr << "Too many parameter";
    }

    return 0;
}
