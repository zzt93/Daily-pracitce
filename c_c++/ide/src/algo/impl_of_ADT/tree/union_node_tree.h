
typedef union {
    double d;
    char *str;
    int i;
    char c;
} Data;

typedef enum {DOUBLE, STRING, INT, CHAR} Type;
typedef enum {PREORDER, INORDER, POSTORDER} Order;

struct T{
    Data data;
    Type type;
    struct T *left;
    struct T *right;
};

typedef struct T TNode;