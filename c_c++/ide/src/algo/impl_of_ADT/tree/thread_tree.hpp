
template <class T>
struct Thread_tree {

    struct Thread_node {
        T t;
        Thread_node * left;
        Thread_node *right;
        bool hasChildren;
    };

    void in_order();
};