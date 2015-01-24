#include <iostream>
#include "console_reader.cpp"
#include "file_reader.cpp"

using std::cout;
using std::endl;

void test_console_reader(){
    Console_reader r;
    string s;
    while (r.next_line(s)){
    }
    vector<string> lines = r.lines();
    for (unsigned int i = 0; i < lines.size(); ++i) {
        cout << lines[i] << endl;
    }
    r.reset();
    while (r.next_word(s)) {
    }
    vector<string> words = r.words();
    for (unsigned int i = 0;i < words.size(); ++i) {
        cout << words[i] << " " ;
    }
}
void test_file_reader(){
    File_reader r("data");
    /*
    string s;
    while (r.next_line(s)){
    }
    vector<string> lines = r.lines();
    for (unsigned int i = 0; i < lines.size(); ++i) {
        cout << lines[i] << endl;
    }
    r.reset();
    */
    int i;
    while (r.next_int(i)) {
        cout << i << " ";
    }
    cout << endl;
}
int main(int argc, char *argv[])
{
    //test_console_reader();
    test_file_reader();
    return 0;
}
