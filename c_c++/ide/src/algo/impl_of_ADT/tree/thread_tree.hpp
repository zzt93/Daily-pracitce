template <class T>
class Thread_node {
    T t;
    Thread_node * left;
    Thread_node *right;
    bool has_left;
    bool has_right;
};


template <class T>
class Thread_tree {
    Thread_node *root;

    void in_order();
    void pre_order();
    void post_order();

public:
    Thread_tree(){}
    ~Thread_tree(){
    }
    //default is in_order
    void make_in_order();
    /*
      using the in order thread tree,
      you can traverse the tree without recursion and stack
    */
    void in_order_thread();

    //make to pre_order by updating index
    void make_pre_order();
    void pre_order_thread();

    //make to post_order by updating index
    void make_post_order();
    void post_order_thread();
};