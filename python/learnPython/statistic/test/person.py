from __future__ import division
from numpy import mean, std
from math import sqrt
from scipy.stats import t

__author__ = 'zzt'


def pers(x, y):
    assert len(x) == len(y)

    x_bar = mean(x)
    y_bar = mean(y)
    s_x = std(x, ddof=1)
    s_y = std(y, ddof=1)
    tmp = 0.0
    for i in range(0, len(x)):
        tmp += (x[i] - x_bar) * (y[i] - y_bar) / s_x / s_y

    r = tmp / (len(x) - 1)
    if r == 1:
        return [1, 0]
    tt = r * sqrt((len(x) - 2) / (1 - r ** 2))
    p = t.sf(abs(tt), len(x) - 2) * 2
    return [r, p]


class Solution():
    def pearsonr(self, x, y):
        if len(y) == len(x) == 0:
            return [None, None]
        return self.res(pers(x, y))

    def res(self, a):
        return [round(x, 6) for x in a]


if __name__ == '__main__':
    l = [1, 2, 3]
    ll = [2, 2, 3]
    # print(pers(l, ll))
    s = Solution()
    print(s.pearsonr(l, ll))
