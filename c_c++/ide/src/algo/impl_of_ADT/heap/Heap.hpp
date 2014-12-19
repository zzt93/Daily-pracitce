#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>

bool is_2power(int);
std::ostream& print_space(std::ostream&, int);

static const int INVAILD = -1;

template <class T>
class Heap{

    bool max;
    std::vector<T> nodes;
    void percolate_down(int b);
    void percolate_up_add(int b);
    void percolate_up(int b);
    
public:
    Heap(bool max){
        this->max = max;
    }
    Heap(bool max, T t){
        this->max = max;
        nodes.add(t);
    }
    Heap(bool max, std::vector<T> vec):max(max), nodes(vec){
        if (max) {
            adjust_to_heap_down();
        } else {
            adjust_to_heap_up();
        }
    }

    Heap(const Heap& h){
        nodes = h.get_node();
    }
    virtual ~Heap(){
    }

    /* Getter */
    
    T top() const{
        return nodes.front();
    }
    T pop() const{
        T t = nodes.front();
        nodes[0] = nodes.back();
        nodes.pop_back();
        percolate_down(0);
        return t;
    }
    std::vector<T> get_node () const{
        return nodes;
    }


    friend std::ostream& operator << (std::ostream& os, Heap<T> heap){
        std::vector<T> nodes = heap.get_node();
        if(nodes.size() == 0){
            os << "Heap is empty" << std::endl;
            return os;
        }
        os << "Heap:" << std::endl;
        ::print_space(os, nodes.size()/2);
        int cout_of_line = 0;
        for (int i = 0; i < nodes.size(); ++i){
            os << nodes[i] << " ";
            if ( ::is_2power(i+2) ){//2^n -2
                os << std::endl;
                cout_of_line++;
                ::print_space(os, nodes.size()/2-cout_of_line);
            }
        }
        os << "end" << std::endl;
        return os;
    }
    
    /* Functions to change the state of object*/
    void add(T a){
        nodes.push_back(a);
        percolate_up_add(nodes.size()-1);
    }


    void adjust_to_heap_down();
    void adjust_to_heap_up();
    /*
      parameters: i1 and i2 is the index of nodes, max is whether this heap is max or min
      pre-request:    i1
                     /  \
                    i2
    */
    int cmp_swap(int i1 , bool max){
        int i2 = 2*i1+1;
        int which_child = 0;
        if(max) {
            if (i2+1 < nodes.size()) {
                if(nodes[i2+1] > nodes[i2]){
                    ++i2;
                    which_child++;
                }
            }
            if (nodes[i1] < nodes[i2]){
                T temp = nodes[i1];
                nodes[i1] = nodes[i2];
                nodes[i2] = temp;
            }
        }else {
            if (i2+1 < nodes.size()) {
                if(nodes[i2+1] < nodes[i2]){
                    ++i2;
                    which_child++;
                }
            }
            if (nodes[i1] > nodes[i2]){
                T temp = nodes[i1];
                nodes[i1] = nodes[i2];
                nodes[i2] = temp;
            }
        }
        return which_child;
    }
};

/*
  This function compare the node, his siblings and its father node
  So this function should be only invoke once at one father node
*/
template <class T>
void Heap<T>::percolate_down(int i){
    for (; 2*i+1 < nodes.size(); ) {
        i = 2*i+1 + cmp_swap(i, max);// the cmp_swap will tell which child node to go
    }
}

/*
  This function just compare the node and its father node
*/
template <class T>
void Heap<T>::percolate_up_add(int i){
    for (; i != 0 && i/2 >= 0; i/=2) {
        int tei = i/2;
        if (i%2 == 0) {//i/2-1 >= 0
            tei -= 1;
        }
        if (max) {
            if (nodes[tei] < nodes[i]){
                T temp = nodes[i];
                nodes[i] = nodes[tei];
                nodes[tei] = temp;
            }
        } else {
            if (nodes[tei] > nodes[i]){
                T temp = nodes[i];
                nodes[i] = nodes[tei];
                nodes[tei] = temp;
            }
        }

    }
}
/*
  This function compare the node, his siblings and its father node
  So this function should be only invoke once at one father node
*/
template <class T>
void Heap<T>::percolate_up(int i){
    for (; i != 0 && i/2 >= 0; i/=2) {
        int tei = i/2;
        if (i%2 == 0 && i/2-1 >= 0) {
            tei -= 1;
        }
        cmp_swap(tei, max);
    }
}

/*
  This method is not right for the time being
  because the algorithm of this method is not clear enough now
*/
template <class T>
void Heap<T>::adjust_to_heap_up(){
    int last = nodes.size()-1;
    for(int i = last; i > last/2; i-=1){//the step of i is not in clarity
        percolate_up(i);
    }
}
template <class T>
void Heap<T>::adjust_to_heap_down(){
    int middle = (nodes.size()-1)/2;
    for(int i = middle; i >= 0; i--){
        percolate_down(i);
    }
}

bool is_2power(int i){
    if ( (i&(i-1)) != 0){
        return false;
    }
    return true;
}

std::ostream& print_space(std::ostream& os, int i){
    while (--i) {
        os << " ";
    }
    return os;
}
