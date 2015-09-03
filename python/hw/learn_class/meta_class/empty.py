__author__ = 'zzt'


class Empty:
    pass


if __name__ == '__main__':
    e = Empty()
    e.a = 1
    e.b = '...'
    e.c = lambda x: x ** 2

    e1 = Empty()
    print(e.c(10))
