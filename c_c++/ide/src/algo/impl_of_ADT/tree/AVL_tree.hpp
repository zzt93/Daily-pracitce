
template <class T>
struct AVL_tree {

    struct AVL_node{
        T t;
        AVL_tree *left;
        AVL_tree *right;
        int length;
    };

    void insert(T t);
    bool deleteNode(T t);
    
private:
    bool checkBlance(const AVL_node &);
};