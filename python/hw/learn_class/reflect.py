__author__ = 'zzt'


class Foo:
    def hello(self):
        print('hello')


if __name__ == '__main__':
    # without reflection
    obj = Foo()
    obj.hello()

    # with reflection
    class_name = "Foo"
    method = "hello"
    obj = globals()[class_name]()
    getattr(obj, method)()

    # with eval
    eval("Foo().hello()")
