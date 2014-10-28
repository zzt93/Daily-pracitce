#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;

/*
  delete every char in s2 that show in s1
*/
int squeeze1(char *s1, const char *s2){
    int count = 0, length = 0;
    char *duS1 = s1;
    while(*s1++ != '\0'){
        length++;
    }
    char *tem = (char *)malloc(sizeof length);
    bool exit = false;

    s1 = duS1;
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
    //copy back
    while (*tem != '\0') {
        *duS1++ = *tem++;
    }
    *duS1 = '\0';
    return count;
}

int squeeze2(char *s1, char *s2){
    int count = 0, length = 0;
    char *duS1 = s1;
    while (*s1++ != '\0') {
        ++length;
    }

    long sign = 0;//employ the every bit to mark whether it is duplicated
    //assume that length is smaller than 64
    s1 = duS1;
    while (*s1 != '\0') {
        while (*s2 != '\0') {
            if(*s1 == *s2){
                count++;
                sign = sign | (1 << (s1 - duS1));
                break;
            }
            ++s2;
        }
        ++s1;
    }
    //copy in original array
    int i = 0;
    int last = 0;
    int last_i = 0;
    int first = 1;
    while (i < length) {
        if((sign & (1 << i) == 0)&&(last == 1)){
            duS1[last_i++] = duS1[i];
        } else if((sign & (1 << i) == 1) && last == 0 && first == 1) {
            first++;
            last_i = i;
        }
        last = sign & (1 << i);
        ++i;
    }
    return count;
}

/*
  we assume that:
  1.when the length of replace is larger than that of find,
  the s has enough place to hold the whole string (ie. the tail of s has some free space)
  2.none of the three strings have '\n'
*/
int find_replace_str(char *s, const char *find, const char *replace){
    int count = 0;
    size_t lenF = strlen(find);
    size_t lenR = strlen(replace);
    const char *duf = find;
    const char *dur = replace;
    char *dus = s;
    char *ss = s;
    if (lenF > lenR) {
        //find the string and replace it with re+1... then squeeze
        while (*s != '\0') {
            ss = s;
            find = duf;
            replace = dur;
            
            while(*ss++ == *find++);
            if(*find == '\0'){//means match
                count++;
                ss = s;
                find = duf;
                //copy
                while (*find != '\0') {
                    if (replace-dur < lenR) {
                        *ss++ = *replace++;
                    } else {
                        *ss++ = '\n';
                    }
                }
                s = ss;// find from the end of last find-string
            } else {
                ++s;// find from next char
            }
        }
        squeeze1(dus, "\n");
        return count;
    } else if (lenR > lenF) {
        //first find how many times need to expand the string, then copy from and back
        while (*s != '\0') {
            ss = s;
            find = duf;
            replace = dur;
            
            while(*ss++ == *find++);
            if(*find == '\0'){//means match
                count++;
                s = ss;// find from the end of last find-string
            } else {
                ++s;// find from next char
            }
        }
        char *temp = (char *)malloc(strlen(dus)+count*(lenR-lenF));
    } else {
        while (*s != '\0') {
            ss = s;
            find = duf;
            replace = dur;
            
            while(*ss++ == *find++);
            if(*find == '\0'){//means match
                count++;
                ss = s;
                find = duf;
                //copy
                while (*find != '\0') {
                    *ss++ = *replace++;
                }
                s = ss;// find from the end of last find-string
            } else {
                ++s;// find from next char
            }
        }
        return count;
    }         
}

int main(int argc, char *argv[])
{
    if (argc <= 2) {
        cout << "Usage: ./a.out str str (squeeze)" << endl;
        cout << "Or: ./a.out str strToFind strToReplace" << endl;
    } else if (argc == 3) {
        squeeze1(argv[1], argv[2]);
        cout << argv[1];
    } else if(argc == 4) {
        find_replace_str(argv[1], argv[2], argv[3]);
        cout << argv[1];
    } else {
        cerr << "Too many parameter";
    }

    return 0;
}
