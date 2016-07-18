#include <iostream>
#include <map>
#include <vector>

using std::endl;
using std::cout;

using namespace std;

class Graph {
private:
	vector<Node*> nodes;
	Node *start;

public:
	Graph(string s) {
		for(char &c: s) {
		}
	}
	~Graph() {
		for(Node * n: nodes) {
			delete n;
		}
	}
};

class Node {
private:
	map<char, Node*> out;
	char c;
public:
	Node(char c) {
		this.c = c;
	}

	
	
};

bool match(string s, string p) {
	Graph g(p);
}

int main(int argc, char *argv[]){

	return 0;
}
