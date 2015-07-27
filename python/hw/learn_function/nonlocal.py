__author__ = 'zzt'

x = 100


def read_only():
    x = 1

    def f1():
        print('In read_only(): ' + str(x))

    f1()


def read_write():
    x = 1

    def f2():
        x = 2
        print('In read_write(): ' + str(x), end=' ')

    f2()
    print(x)


def non_local():
    x = 1

    def f3():
        nonlocal x
        x = 3
        print('In non_local(): ' + str(x), end=' ')

    f3()
    print(x)


def global_var():
    x = 4

    def f4():
        global x
        print('In global_var(): ' + str(x))

    f4()


n = 100


def nested():
    # nonlocal n
    # print(n)
    n = 0

    def n1():
        n = 1

        def n2():
            # nonlocal n
            # global n
            print('In nest(): ' + str(n))

        n2()

    n1()


def scope_test():
    def do_local():
        spam = "local spam"

    def do_nonlocal():
        nonlocal spam
        spam = "nonlocal spam"

    def do_global():
        global spam
        spam = "global spam"

    spam = "test spam"
    do_local()
    print("After local assignment:", spam)
    do_nonlocal()
    print("After nonlocal assignment:", spam)
    do_global()
    print("After global assignment:", spam)


if __name__ == '__main__':
    read_only()
    read_write()
    non_local()
    global_var()
    nested()
    scope_test()
    print("In global scope:", spam)
