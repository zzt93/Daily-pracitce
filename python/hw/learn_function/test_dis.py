import dis

__author__ = 'zzt'


def f():
    l = [0] * 2

'''
  6           0 LOAD_CONST               1 (0)
              3 BUILD_LIST               1
              6 LOAD_CONST               2 (2)
              9 BINARY_MULTIPLY
             10 STORE_FAST               0 (l)
             13 LOAD_CONST               0 (None)
             16 RETURN_VALUE

'''
if __name__ == '__main__':
    dis.dis(f)
