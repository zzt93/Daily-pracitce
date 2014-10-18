
typedef union {
    double x;
    char *str;
    int a;
    char c;
} Data;

typedef struct {
    Data d;
    TNode *left;
    TNode *right;
} TNode;