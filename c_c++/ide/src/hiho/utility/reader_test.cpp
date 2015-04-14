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
    r.reset();
    int i =0;
    r.skip_line(1);
    while (r.next_hex(i)) {
        r.skip_char(999);
        cout << i << " ";
        r.skip(1, ',');
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
    test_console_reader();
    //test_file_reader();
    /*
    vector<int> nums;
    int i = 0;
    while (r.next_int(i)){
        //int me = median_heap(i);
        nums.push_back(me);
    }
    */
    return 0;
}
