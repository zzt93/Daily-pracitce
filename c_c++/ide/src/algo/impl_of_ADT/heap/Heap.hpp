
Template <class T>
class Heap{

    Node root;
    
    class Node{
    };
    
public:
    Heap();
    Heap(Heap&);
    virtual ~Heap();

    void initialize(T[]);
    T& top();
    void add(T);

    void percolate_down();
    void percolate_up();
};