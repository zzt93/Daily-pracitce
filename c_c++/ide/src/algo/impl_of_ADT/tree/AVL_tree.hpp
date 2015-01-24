template <class T>
class AVL_node{
    T t;
    int mgap;

public:
    
    AVL_tree<T> *left;
    AVL_tree<T> *right;
    int gap() const {
        return mgap;
    }
};


template <class T>
class AVL_tree {
private:
    AVL_node<T> *root;
    
    bool checkBlance(const AVL_node &n){
        if (n.gap() > 1){
            return false;
        }
        return true;
    }
public:
    void insert(T t);
    bool deleteNode(T t);
    
};