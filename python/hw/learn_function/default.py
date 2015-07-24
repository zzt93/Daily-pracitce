__author__ = 'zzt'


def f1():
    i = 5

    def f(arg=i):
        print(arg)

    i = 6
    f()


def f2(a, l=[]):
    l.append(a)
    return l


def f3(a, l=None):
    if l is None:
        l = []

    l.append(a)
    return a


if __name__ == '__main__':
    f1()
    for x in range(5):
        print('In f2:' + str(f2(x)))
        print('In f3:' + str(f3(x)))
