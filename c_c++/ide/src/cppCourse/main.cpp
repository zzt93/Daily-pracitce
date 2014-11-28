#include <iostream>
#include <fstream>
//#include <ofstream>
#include <string>
#include <cstring>
#include <cstdlib>
//#include <ctype>


using namespace std;


bool legalStr(char *str, int size){
	char *s = str;
	while(size--){
		if(!isalpha(*s) && *s != '+' && *s != '-' && *s != '*'){
			return false;
		}
		s++;
	}
	return true;
}

void mylower(char *a, int size){
	while(size--){
		if(isupper(*a)){//TODO
			*a = tolower(*a);
		}
		++a;
	}
}

void asp(char *s, int size){
	while(size--){
		if(*s == '*'){
			*s = 's';
			break;
		}
		++s;
	}
}

void code_plus(char *s, int size ){
	while(size--){
		if(*s == '+'){
			strcpy(s, s+1);//TODO
			break;
		}
		++s;
	}
}

int findSpace(char *s){
	int i =0;
	while(*s && !isspace(*s)){
		++i;
		++s;
	}
	return i;
}

void code_minus(char *s, int si){
	char word[100] = {0};
	int size = 0;
	char *start = s;
	char *dus = s;
	while(s){
		if(*s == '-'){
/*			size = strlen(s);
			strncpy(word, s+1, size);//copy out the word
			strcpy(s, s+size+1);//override the '-bdc'
			if(s = start){//-aa
				strcpy(s, s+size );
			} else {
				dus = s;
				while(s != start){
					--s;
					if(has(*s, word)){
						strcpy(s, );
					}
				}
			}*/
			++s;
			while(*s != '\0'){
				if(*dus == *s){
				strncpy(dus, dus+1, s-dus);
			}
				++s;
			}
			break;
		}
		++s;//TO
	}
}

bool split(char *s, int *length){
    if (*s == '\0'){
        return false;
    }
    while (*s == ' ') {
        ++s;
    }
    int i = 0;
    while (*s != '\0' && *s != ' ') {
        ++i;
        ++s;
    }
    *length = i;
}


void function1(const char *path){
	ifstream in(path);
	ofstream out1("D:\\131250072_1.txt");
	char a[1024] = {0};//TODO
	char *s = a;
	char word[100] = {0};
	char empty[100] = {0};

	if (in.is_open() && out1.is_open()){
		while(in.getline(a, 1024)){
			s = a;
			cout << "debug" << endl;
			while(s){
			cout << "debug in s" << endl;
				int size = 0;
				char *dua = s;
				while(isspace(*s)){
					s++;
				}//ignore opening space
				dua = s;
				while(!isspace(*s)){
					++s;
					++size;
				}
				strncpy(word, dua, size);
			//	cout << word << endl;
				if(legalStr(word, size)){
					cout << "in it" << endl;
					mylower(word, size);
					asp(word, size);
					code_plus(word, size);
					code_minus(word, size);
					out1 << word << " " ;
				}
				strncpy(word, empty, 100);
			}
			out1 << '\n';
		}
		//cout << "debug" << endl;
	} else {
		cout << "no file" << endl;
	}
}

int main(int argc, char *argv[]){
	if(argc == 1){
		cout << "no file path";
		return 0;
	}
	
    //	function1(argv[1]);

/*	char a[] = {'a', 'b', 'c', '\0'};
	strcpy(a, a+1);
	puts(a);*/
	char a[10] = {0};
	a[0] = 'a';
	a[1] = 'b';
	a[2] = 'c';
	a[3] = 'd';
	char *s = a;
	while(*s){
		cout << *s++ << endl;
	}

	return 0;
}