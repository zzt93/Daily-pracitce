
template <class T>
struct Index_tree {
    
    struct Index_tree_node{
        T t;
        Index_tree_node *left;
        Index_tree_node *right;
        int left_size;
    };
    
    bool has(T t);
    Index_tree_node *find(T t);
    bool deleteNode(T t);

};
