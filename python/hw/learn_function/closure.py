__author__ = 'zzt'

'''
While closure1 and closure2 refer to the same function sum,
the associated environments differ, and invoking the
closures will bind the name x to two distinct variables with different values
in the two invocations, thus evaluating
the function to different results
'''


def f(x):
    def sum(y):
        return x + y

    return sum


if __name__ == '__main__':
    closure1 = f(1)
    closure2 = f(5)
