__author__ = 'zzt'


def get_class(fun):
    class t:
        pass

    setattr(t, fun.__name__, fun)
    return fun


def make_class(**kwargs):
    return type('MyClass', (object,), dict(**kwargs))


def hello():
    print('hello')


if __name__ == '__main__':
    # old way
    Foo = get_class(hello)
    # foo = Foo()
    # foo.hello()
    # another old way
    # from new import classobj
    #
    # Foo2 = classobj('Foo2', (Foo,), {'bar': lambda self: 'bar'})
    # Foo2().bar()
    # Foo2().say_foo()
    Foo = make_class(f=hello, a=3)
    Foo3 = type('Foo3', (Foo,), {'bar': lambda self, a: a + 1})
    print(Foo3)
    print(Foo3().bar(2))
