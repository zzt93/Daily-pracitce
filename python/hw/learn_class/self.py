__author__ = 'zzt'


def f():
    s = 'In f'
    print(s)
    return s


"""
When an instance attribute is referenced that isn’t a data attribute,
its class is searched. If the name denotes a valid class attribute
that is a function object, a method object is created
by packing (pointers to) the instance object and the function object
just found together in an abstract object:
this is the method object.
When the method object is called with an argument list,
a new argument list is constructed from the instance object
and the argument list, and the function object is called
with this new argument list.
"""


class A:
    i = 123  # class variable shared by all instances

    def __init__(self, i):
        self.x = i  # instance variable unique to each instance

    def f(self):
        s = 'In A.f'
        print(A.i)
        print(self.x)
        print(s)
        return s


if __name__ == '__main__':
    print(f)
    f()
    print(A.i)
    print(A.f)
    # print(A.f())
    a = A(1)
    print(a.f)
    a.f()
    A.f(a)
