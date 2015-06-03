__author__ = 'zzt'


def average(l):
    return sum(l) / len(l)


def ssum(l):
    aver = average(l)
    ssum = 0.0
    for x in l:
        ssum += (x - aver) ** 2

    return ssum


def sigma(l):
    return ssum(l) / len(l)


def s(l):
    return ssum(l) / (len(l) - 1)
