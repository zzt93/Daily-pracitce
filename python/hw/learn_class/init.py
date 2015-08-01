__author__ = 'zzt'


class A:
    def f(self):
        print('In f')


if __name__ == '__main__':
    """
    In c++:
    // this means create an object on the stack, so shouldn't
    assign to others like `A a = A();`(which cause a new object being created)
    // also notice `A a();` is invalid for is so much like a function declaration
    A a;
    A* a = new A;

    In Java:
    // default behaviour is allocating objects on the heap
    and just store the 'pointer' in the variable
    A a = new A();

    In python:
    variables also store the references
    """
    a = A()
