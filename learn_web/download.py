from requests import get
import sys

__author__ = 'zzt'


def download(url, name='../resource/res'):
    r = get(url)
    print(r.status_code)
    with open(name, 'wb') as f:
        f.write(bytes(r.text, 'utf-8'))


class A:
    pass

if __name__ == '__main__':
    # a = A()
    # a.f = lambda x: x ** 2
    # a.a = 1
    # print(a.a)

    # b = A()
    # # AttributeError: 'A' object has no attribute 'a'
    # print(b.a)

    if len(sys.argv) == 3:
        download(sys.argv[1], sys.argv[2])
    else:
        print('python download.py url filename')
