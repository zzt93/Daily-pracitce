
class Union {
    int *a;

    int find(int p);
public:
    Union(int n){
        a = new int[n];
    }
    ~Union(){
        delete[] a;
    }

    bool connected(int p, int q);
    void union(int p, int q);
};