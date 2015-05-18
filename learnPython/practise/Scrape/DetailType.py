from enum import Enum

__author__ = 'zzt'


class DetailType(Enum):
    traditional = 1
    advanced = 2
    misc = 3
    scoring = 4
    usage = 5


if __name__ == '__main__':
    for t in DetailType:
        print(t)
