from __future__ import division
import numpy as np

__author__ = 'zzt'


def average(l):
    return sum(l) / len(l)


def ssum(l):
    aver = average(l)
    ssum = 0.0
    for x in l:
        ssum += (x - aver) ** 2

    return ssum


def ssum2(l):
    lSq = [x ** 2 for x in l]
    first = sum(lSq)
    sec = sum(l) ** 2 / len(l)

    return first - sec


def sigma_2(l):
    return ssum(l) / len(l)


def s_2(l):
    return ssum(l) / (len(l) - 1)


def get_data(mu, sigma, size=100):
    x = mu + sigma * np.random.randn(size)
    return x


if __name__ == '__main__':
    # l = [1, 2, 3, 4, 5]
    # print(ssum(l))
    # print(sigma_2(l))
    sigma = 15
    x = get_data(100, sigma)
    print('standard: {0:5f} , first: {1}, sec: {2}'.format(
        np.var(x), sigma_2(x), ssum(x) / len(x)))
