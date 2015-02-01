#include <iostream>
#include <exception>
#include <stack>
#include <string>
#include <iterator>
#include <vector>
#include <map>

using std::vector;
using std::exception;
using std::string;
using std::stack;
using std::map;
using std::back_inserter;

using std::cout;
using std::endl;
using std::stoi;

using std::getline;
using std::cin;
using std::istream;

#ifndef _my_reader_
#define _my_reader_


class Reader {

public:
    Reader(){}

    virtual istream& next_word(string&) = 0;
    virtual istream& readwords() = 0;
    virtual istream& next_line(string&) = 0;
    virtual istream& readlines() = 0;
    virtual vector<string> lines() const = 0;
    virtual vector<string> words() const = 0;

};

#endif

class Console_reader: Reader {
    vector<string> mlines;
    vector<string> mwords;

public:
    Console_reader(){}

    istream& next_word(string& s) {
        cin >> s;
        mwords.push_back(s);
        return cin;
    }
    istream& readwords() {
        string s;
        while (next_word(s)){
        }
        return cin;
    }
    istream& next_int(int& i){
        string s;
        cin >> s;
        if (cin){
            i =  std::stoi(s);
        }
        return cin;
    }
    istream& next_double(double& d){
        string s;
        cin >> s;
        if (cin){
            d = std::stod(s);
        }
        return cin;
    }
 
    istream& next_line(string& s) {
        getline(cin, s);
        mlines.push_back(s);
        return cin;
    }
    istream& readlines() {
        string s;
        while (next_line(s)) {
        }
        return cin;
    }
    vector<string> lines() const{
        return mlines;
    }
    vector<string> words() const{
        return mwords;
    }

    void reset () {
        cin.clear();
        //    cin.ignore();
    }
    void clear_words(){
        mwords.clear();
    }
    void clear_lines(){
        mlines.clear();
    }
};

class Node {
    char c;
    long count;
    map<char, Node*> sons;

public:
    Node(char ch): c(ch) {
        count = 1;
    }

    char getChar(){
        return c;
    }
    bool has_son(Node *n){
        return sons.count(n->c);
    }
    bool has_son(char c){
        return sons.count(c);
    }
    Node* get_son(char c){
        return (sons.find(c)->second);
    }
    void add_son(Node* son){
        sons[son->c] = son;
    }
    map<char, Node*>::iterator first_son() {
        return sons.begin();
    }
    bool has_son(map<char, Node*>::iterator it){
        return it != sons.end();
    }
    map<char, Node*>::iterator next_son(map<char, Node*>::iterator it){
        if(has_son(it)){
            auto th = sons.find(it->first);
            return std::next(th);
        }
        throw exception();
    }
    void visit(){
        count ++;
    }
    long times(){
        return count;
    }
};


class Trie_tree {
    vector<Node*> nodes;
    Node* root;
    
    void add(Node *temp){
        nodes.push_back(temp);
    }
    template <class Out>
    void dfs(Out out, Node* root);
    
    template <class Out>
    void begin_with(Out out, string s);

public:
    Trie_tree(){
        root = new Node('a'-1);
    }
    virtual ~Trie_tree(){
        for (unsigned int i = 0; i < nodes.size(); ++i) {
            delete nodes[i];
        }
    }

    void add(string s){
        Node* temp = root;
        for (unsigned int i = 0; i < s.size(); ++i) {
            if (temp->has_son(s[i])) {
                temp = temp->get_son(s[i]);
                temp->visit();
            } else {
                Node *ne = new Node(s[i]);
                temp->add_son(ne);
                add(ne);
                temp = ne;
            }
        }
    }
    long search(string s){
        Node* temp = root;
        for (unsigned int i = 0; i < s.size(); ++i) {
            if (temp->has_son(s[i])) {
                temp = temp->get_son(s[i]);
            } else {
                return 0;
            }
        }
        return temp->times();
    }
    template <class Out>
    void dfs(Out out){
        dfs(out, root);
    }
    
    template <class Out>
    void begin_with(Out out){
        begin_with(out, root);
    }
    
};


// FIXME: let to rewrite
template <class O>
void write_stack(O o, stack<char> s){
    stack<char> reverse;
    for (char a = s.top(); a != 'a'-1; s.pop()) {
        reverse.push(a);
        a = s.top();
    }
    
}
// FIXME: let to rewrite
template <class Out>
void Trie_tree::begin_with(Out out, string s){
    vector<string> res;
    stack<char> stack;
    Node* temp = root;
    for (unsigned int i = 0; i < s.size(); ++i) {
        if (temp->has_son(s[i])) {
            temp = temp->get_son(s[i]);
            stack.push(s[i]);
        } else {
            return;
        }
    }
    stack.push('a'-1);//add a separator 
    // find the left part of words begin with it
    vector<Node*> left;
    dfs(back_inserter(left), temp);
    for (unsigned int i = 0; i < left.size(); ++i) {
        if (left[i] != NULL) {
            stack.push(left[i]->getChar());
        } else {
            write_stack(back_inserter(res), stack);
        }
    }
}

template <class Out>
void Trie_tree::dfs(Out out, Node* root){
    *out++ = root;
    if(root == NULL){
        return;
    }
    map<char, Node*>::iterator son = root->first_son();
    for (; root->has_son(son) ; son = (root->next_son(son))) {
        dfs(out, son->second);
    }
    *out++ = NULL;
}



int main()
{
    Console_reader r;
    r.readlines();
    vector<string> lines = r.lines();
    unsigned int size = stoi(lines[0]);
    Trie_tree tree;
    for (unsigned int i = 1; i <= size; ++i) {
        tree.add(lines[i]);
    }
    vector<Node*> res;
    tree.dfs(back_inserter(res));
    int times = stoi(lines[size+1]);
    for (unsigned int i = 2+size; i <= size+times+1; ++i) {
        cout << tree.search(lines[i]) << endl;
    }
    return 0;
}
