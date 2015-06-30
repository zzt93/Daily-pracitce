# import copy
from __future__ import division
from numpy.ma import mean, var
from scipy.stats import norm, skew, kurtosis

__author__ = 'zzt'

# -*- coding:utf-8 -*-


class Solution:
    def __init__(self):
        pass

    def describe(self, b):
        a = list(b)
        mean = self.aver(a)
        if len(a) == 1:
            return [mean, None, 0, -3]
        var = sum([(x - mean) ** 2 for x in a]) / (len(a) - 1)
        if var == 0:
            return [mean, 0, -3, 0]
        ske = self.skew(a)
        kurt = self.kurto(a)
        return self.res([mean, var, ske, kurt])

    def aver(self, a):
        s = sum(a)
        return s * 1.0 / len(a)

    def vara(self, a, n):
        mean = self.aver(a)
        b = [(x - mean) ** n for x in a]
        return sum(b) * 1.0 / len(a)

    def skew(self, a):
        var = self.vara(a, 2)
        var3 = self.vara(a, 3)
        return var3 / var ** 1.5

    def kurto(self, a):
        var = self.vara(a, 2)
        var4 = self.vara(a, 4)
        return var4 / var ** 2 - 3

    def res(self, a):
        return [round(x, 6) for x in a]


if __name__ == '__main__':
    s = Solution()
    # norm1 = norm(loc=0, scale=4)
    # l = norm1.rvs(size=1000)
    l = [1, 2, 3]
    print(mean(l), var(l), skew(l), kurtosis(l))
    print(s.describe(l))
    # print(norm1.stats(moments='mvsk'))
