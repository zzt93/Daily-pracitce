from learn_class.iterator import shuffle

__author__ = 'zzt'


def reverse(data):
    for index in range(len(data) - 1, -1, -1):
        yield data[index]


def random(data):
    shuffle(data)
    for index in range(len(data)):
        yield data[index]

if __name__ == '__main__':
    l = list('abcdefghijklmn')
    for c in l:
        print(c, end=',')
    print()
    for c in reverse(l):
        print(c, end=',')
    print()
    for c in random(l):
        print(c, end=',')