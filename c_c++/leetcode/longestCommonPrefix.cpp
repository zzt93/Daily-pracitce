#include <iostream>
#include <map>
#include <vector>
#include <cassert>

using std::endl;
using std::cout;

using namespace std;

class Node {
private:
	char c;
	map<char, Node*> children;
	int count;
public:
	Node(char c) {
		this->c = c;
		count = 1;
	}	
	Node(char c, int s) {
		this->c = c;
		count = s;
	}
	bool all_reach(int target) {
		return count == target;
	}
	Node* add_child(Node *n) {
		auto it = children.find(n->c);
		if (it != children.end()) {
			it->second->count++;
			return it->second;
		}
		children[n->c] = n;
		return n;
	}
	char get_char() {
		return c;
	}
	Node* move() {
		return children.begin()->second;
	}
	bool empty() {
		return children.empty();
	}
};

string longest(vector<string>& strs) {
	int size = strs.size();
	Node *root = new Node('\0', size);
	for(string& s: strs) {
		Node *start = root;
		for(char& c: s) {
			start = start->add_child(new Node(c));
		}
	}
	string res;
	if (root->empty()) {
		return "";
	}
	root = root->move();
	while(root->all_reach(size)) {
		res += root->get_char();
		if (root->empty()) {
			break;
		}
		root = root->move();
	}
	return res;
}		
	
int main(int argc, char *argv[]){
	vector<string> a = {"abc", "abcd", ""};
	cout << longest(a) << endl;
	return 0;
}
