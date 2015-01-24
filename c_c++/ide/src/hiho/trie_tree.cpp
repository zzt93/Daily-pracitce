#include <iostream>
#include <stack>
#include <string>
#include <iterator>
#include <vector>
#include <map>

using std::vector;
using std::string;
using std::stack;
using std::map;
using std::back_inserter;

class Node {
    char c;
    long count;
    map<char, Node*> sons;

public:
    Node(char ch): c(ch) {
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
    auto first_son(){
        return sons.begin();
    }
    bool has_son(map<char, Node*>::iterator it){
        return it != sons.end();
    }
    auto next_son(map<char, Node*>::iterator it){
        if(has_son(it)){
            auto th = sons.find(it);
            return std::next(th);
        }
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
                temp->visit();
                temp = temp->get_son(s[i]);
            } else {
                Node *ne = new Node(s[i]);
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
    void dfs(Out out, Node* root);
    
    template <class Out>
    void begin_with(Out out, string s);
    
};

template <class O>
void write_stack(O o, stack<char> s){
    stack<char> reverse;
    for (char a = s.top(); a != 'a'-1; s.pop()) {
        reverse.push(a);
        a = s.top();
    }
    
}

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
    Node* son = root->first_son();
    for (; son != NULL; son = (root->next_son(son))->second) {
        *out++ = son;
        dfs(out, son);
    }
    *out++ = NULL;
}