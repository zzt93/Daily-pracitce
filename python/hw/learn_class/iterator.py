import random

__author__ = 'zzt'


def swap(data, i, x):
    data[i], data[x] = data[x], data[i]


def shuffle(data):
    for x in range(len(data) - 1, -1, -1):
        i = random.randint(0, x)
        swap(data, i, x)


class Random:
    """ iterator for looping in a random order
    """

    def __init__(self, data):
        self.data = data
        self.len = len(data)
        self.index = [x for x in range(self.len)]
        shuffle(self.data)

    def __iter__(self):
        return self

    def __next__(self):
        if self.len == 0:
            raise StopIteration
        self.len -= 1
        return self.data[self.index[self.len]]


if __name__ == '__main__':
    for char in Random(list('abcdefg')):
        print(char)
