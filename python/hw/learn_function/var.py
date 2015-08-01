__author__ = 'zzt'


def f():
    a = [1, 2, 3]
    b = ['foo', a]
    a.append(4)
    print(b)


def f2():
    a = [1, 2, 3]
    b = dict()
    b['foo'] = a
    a.append(4)
    print(b)


def f3():
    def immutable(some_int):
        some_int += 1

    a = 1
    immutable(a)
    print(a)


def f4():
    print('---------In f4------')
    # list * 2 is just the copy of every elements' reference
    # so, if the list contain mutable elements,
    a = [[0] * 2] * 2
    a[0][0] = 1
    print(a)

    l = [[0] * 3]
    b = l * 2
    b[0][0] = 1
    print(b)

    c = [[0 for x in range(2)] for y in range(2)]
    print(c)


if __name__ == '__main__':
    f()
    f2()
    f3()
    f4()
