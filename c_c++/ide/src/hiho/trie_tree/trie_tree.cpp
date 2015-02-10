#include <iostream>
#include <deque>
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
using std::deque;
using std::back_inserter;


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
    auto first_son(){
        return sons.begin();
    }
    bool has_son(map<char, Node*>::iterator it){
        return it != sons.end();
    }
    auto next_son(map<char, Node*>::iterator it){
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
    static const char NOT_CHAR = 'a'-1;
    vector<Node*> nodes;
    Node* root;
    
    void add(Node *temp){
        nodes.push_back(temp);
    }
    template <class Out>
    void dfs(Out out, Node* root);
    
    Node* search(Node* r, string s){
        Node* temp = r;
        for (unsigned int i = 0; i < s.size(); ++i) {
            if (temp->has_son(s[i])) {
                temp = temp->get_son(s[i]);
            } else {
                return NULL;
            }
        }
        return temp;
    }
public:
    Trie_tree(){
        root = new Node(NOT_CHAR);
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
        Node* res = search(root, s);
        if (res == NULL) {
            return 0;
        } else {
            return res->times();
        }
    }
    template <class Out>
    void dfs(Out out){
        dfs(out, root);
    }
    
    template <class Out>
    void begin_with(Out out, string s);
    
};


string get_word(string s, deque<Node*>& q, int count){
    unsigned int size = q.size();
    for (unsigned int i = 0; i < size; ++i) {
        Node* t = q.front();
        if (i < size-count*2) {
            s = s + t->getChar();
            q.push_back(t);
        } else if (i < size-count) {
            s = s + t->getChar();
        }
        q.pop_front();
    }
    return s;
}

template <class Out>
void Trie_tree::begin_with(Out out, string s){
    deque<Node*> deque;
    Node *n = search(root, s);
    if (n == NULL){
        return;
    }
    // find the left part of words begin with it
    vector<Node*> left;
    dfs(back_inserter(left), n);
    bool last_is_null = false;
    int count_of_null = 0;
    for (unsigned int i = 1; i < left.size(); ++i) {//i start from 1 for the root is useless
        if (left[i] != NULL && last_is_null) {
            *out++ = get_word(s, deque, count_of_null);
            //update flags
            last_is_null = false;
            count_of_null = 0;
        } else if (left[i] == NULL){
            last_is_null = true;
            count_of_null++;
        }
        deque.push_back(left[i]);
    }
    deque.pop_back();//remove the last null for we leave out the first char
    *out++ = get_word(s, deque, count_of_null-1);
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