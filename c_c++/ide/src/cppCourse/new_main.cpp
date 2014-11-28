#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <cstdlib>

#define STR 1
#define MAX_WORD 100

using namespace std;

const char empty_word[100] = {0};

bool split(char *s, int *length){
    if (*s == '\0'){
        return false;
    }
    int i = 0;
    while (isspace(*s)) {
        ++i;
        ++s;
    }//ignore opening space
    if (*s == '\0'){
        return false;
    }
    while (*s != '\0' && *s != ' ') {
        ++i;
        ++s;
    }
    *length = i;
    return true;
}


int legal_str(char *s, int size){
    int res;
    bool all_char = true;
    while(size--){
        if(*s != '+' && *s != '-' && *s != '*'
            && !isalpha(*s) && !isspace(*s)){
            return 0;
        } else if (*s == '+') {
            res = '+';
            all_char = false;
        } else if (*s == '-'){
            res = '-';
            all_char = false;
        } else if (*s == '*'){
            res = '*';
            all_char = false;
        }
        ++s;
    }
    if (all_char){
        res = STR;
    }
    return res;
}

void asp(char *s, int len){
    for (;len > 0; len--, s++) {
        if(*s == '*'){
            *s = 's';
        }
    }
}
void code_plus(char *s, int len){
    for (;len > 0; --len, ++s) {
        if(*s == '+'){
            strncpy(s, s+1, len-1);
        }
    }
}
char * code_minus(char *s, int len, char *res){
    char * const dus = s;
    for (;len > 0; len--, ++s) {
        if(*s == '-'){
            break;
        }
    }
    int lim = s-dus;
    int i = 0;
    int in_res = 0;
    ++s;//make the pointer point to the char adjacent to '-'
    char *const dus2 = s;
    const int dulen = len;
    for (; i < lim; ++i ) {
        bool del = false;
        s = dus2;
        len = dulen;
        for (; len > 0 && *s; len--, ++s){
            if (dus[i] == *s){
                del = true;
            }
        }
        if(!del || isspace(dus[i])){
            res[in_res++] = dus[i];
        }
    }
    return res;
}

void my_lower(char *s, int size){
    while(size--){
		if(isupper(*s)){//TODO
			*s = tolower(*s);
		}
		++s;
	}
}

fstream back("back.txt", ios::out | ios::in | ios::trunc);
void function1(const char *path){
	ifstream in(path);
    //ofstream out1("D:\\131250072_1.txt");
    ofstream out1("131250072_1.txt");
    char a[1024] = {0};

	if (in.is_open() && out1.is_open() && back.is_open()){
        int index = 0;
        int len = 0;
		while(in.getline(a, 1024)){
            index = 0;
            len = 0;
            while (split(&a[index], &len)){
                switch(legal_str(&a[index], len)) {
                    case '+':
                        code_plus(&a[index], len);
                        my_lower(&a[index], len-1);
                        out1.write(&a[index], len-1);//omit the sign of '+'
                        back.write(&a[index], len-1);
                        break;
                    case '-':
                    {
                        char res[MAX_WORD] = {0};
                        code_minus(&a[index], len, res);
                        my_lower(&a[index], len);
                        out1.write(res, strlen(res));
                        back.write(res, strlen(res));
                        break;
                    }
                    case '*':
                        asp(&a[index], len);
                        my_lower(&a[index], len);
                        out1.write(&a[index], len);
                        back.write(&a[index], len);
                        break;
                    case STR:
                        my_lower(&a[index], len);
                        out1.write(&a[index], len);
                        back.write(&a[index], len);
                        break;
                    default:
                        cout << "illegal char" << endl;
                        break;
                }
                index += len;
            }
            out1 << endl;
            back << " ";
		}
        out1.close();
        back << endl;// without it getline fail to read from it????

	} else {
		cout << "no file" << endl;
	}
}

struct Token {
    char str[MAX_WORD];
    int times;
};

int cmp_token(Token t1, Token t2){
    if (t1.times > t2.times) {
        return 1;
    } else if (t1.times < t2.times) {
        return -1;
    } else {
        return - strcmp(t1.str, t2.str);//the result show should add a '-'
    }
}

int my_strcmp(char *s1, char *s2){
    int len1 = strlen(s1) + 1;
    int len2 = strlen(s2) + 1;
    char *a1 = new char[len1];
    char *a2 = new char[len2];
    strncpy(a1, empty_word, len1);
    strncpy(a2, empty_word, len2);
    char *const dua1 = a1;
    char *const dua2 = a2;
    
    while(*s1){
        if (!isspace(*s1)) {
            *a1++ = *s1;
        }
        s1++;
    }
    while(*s2){
        if (!isspace(*s2)) {
            *a2++ = *s2;
        }
        s2++;
    }
    int res = strcmp(dua1, dua2);
    delete[] dua1;
    delete[] dua2;
    return res;
}

/*
  1.quick sort algorithm : ascending order

  2.tokens [s, e)

  3.if t1 = t2
*/
void sort_token(Token *t, int s, int e){
    if (s >= e){
        return;
    }
    const int dus = s;
    const int due = e;
    --e;//make e point to the last element
    while (s < e) {
        while (s < e && cmp_token(t[dus], t[s]) < 0){
            ++s;
        }
        while (s < e && cmp_token(t[dus], t[e]) > 0){
            --e;
        }
        //swap two tokens that is in reversed order
        if (s < e){
            Token temp = t[s];
            t[s] = t[e];
            t[e] = temp;
        }
    }

    Token sw = t[dus];
    t[dus] = t[e];
    t[e] = sw;

    sort_token(t, dus, s);
    sort_token(t, s+1, due);
}

void extract_word(char *s){
    char *const dus = s;
    char tem[MAX_WORD] = {0} ;
    int i = 0;
    while (*s) {
        if (!isspace(*s)){
            tem[i++] = *s;
        }
        ++s;
    }
    strncpy(dus, tem, MAX_WORD);
}


void function2(){
    ofstream out("131250072_2.txt");
    fstream temp_file("temp.txt", ios::out | ios::in | ios::trunc);//must add ios::trunc to create the file if it is not exist

    if (back.is_open() && out.is_open() && temp_file.is_open()) {
        int count_of_word = 0;
        int diff_word = 0;
        back.seekg(0, ios::end);
        int size = back.tellg();
        //        cout << size << endl;
        char *s = new char[size] ;//save all file in it
        back.seekg(0, ios::beg);//start from beginning, for ios::end
        while (back.getline(s, size)){//TODO why I have to write a endl to make back readable???
            cout << "one time" << endl;
            int beg_of_word = 0;
            int len = 0;
            while (split(&s[beg_of_word], &len)) {//find the first appearence of word
                count_of_word = 1;
                diff_word++;
                
                const int dul = len;
                const int duw = beg_of_word;
                char s1[MAX_WORD] = {0};
                char s2[MAX_WORD] = {0};
                strncpy(s1, empty_word, 100);
                strncpy(s1, &s[duw], dul);
                for (beg_of_word+=len ;split(&s[beg_of_word], &len); beg_of_word+=len) {//scan the rest part, so to count the times of it
                    strncpy(s2, empty_word, 100);
                    strncpy(s2, &s[beg_of_word], len);
                    if (my_strcmp(s1, s2) == 0){//compare strings ignoring the space
                        count_of_word ++;//count of same word
                        memset((void*)&s[beg_of_word], ' ', len);//set the same word in array blank
                    }
                }

                beg_of_word = duw;//find the beginning of last word
                beg_of_word += dul;//to next word

                //write to a file
                extract_word(s1);
                temp_file << count_of_word << endl << s1 << endl;
            }
            //make tokens
            Token *tokens = new Token[diff_word];
            int in_token = 0;
            char tem_w[MAX_WORD] = {0};
            bool num_word = true;//true means it is a num, false means it's a word
            temp_file.seekp(0, ios::beg);//TODO why I have to add it to make it readable??? write to it will change the get position?
            while(temp_file.getline(tem_w, MAX_WORD)){//getline will clear the last word's wreakage
                if (num_word) {
                    tokens[in_token].times = atoi(tem_w);
                    num_word = false;
                } else {
                    strncpy(tokens[in_token++].str, tem_w, MAX_WORD);
                    num_word = true;
                }
            }
            //sort
            sort_token(tokens, 0, diff_word);
            //print
            for (int i = 0; i < diff_word; ++i) {
                out << tokens[i].str << " " << tokens[i].times << endl;
            }

            delete[] tokens;
        }
    } else {
        cout << out.is_open() << endl;
        cout << temp_file.is_open() << endl;
        cout << back.is_open() << endl;
        cout << "can't open file" << endl;
    }
}

int main(int argc, char *argv[]){
	if(argc == 1){
		cout << "no file path";
		return 0;
	}
	
    function1(argv[1]);
    function2();
	return 0;
}