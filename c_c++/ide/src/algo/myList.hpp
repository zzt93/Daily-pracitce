
struct Node{
    double coef;
    double exp;
    Node* next;
    Node* last;
};

bool add(Node* now);
bool insertAt(Node* bef, Node* now);
bool insertAt(int i, Node* now);
bool deleteN(Node* bef);
bool deleteN(int i);
bool hasNext(Node* now);
Node* next(Node* now);
Node* get(int i);
bool clear(Node* from);