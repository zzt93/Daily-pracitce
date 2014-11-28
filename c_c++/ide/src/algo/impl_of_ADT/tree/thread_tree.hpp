
template <class T>
class Thread_tree {

    class Thread_node {
        T t;
        Thread_node * left;
        Thread_node *right;
        bool hasChildren;
    };

    void in_order();
    void pre_order();
    void post_order();

public:
    //default is in_order
    void in_order_thread();

    //make to pre_order by updating index
    void make_pre_order();
    void pre_order_thread();

    //make to post_order by updating index
    void make_post_order();
    void post_order_thread();
};