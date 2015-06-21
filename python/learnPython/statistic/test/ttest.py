from __future__ import division
from numpy import mean, std
from math import sqrt
from scipy import stats
from scipy.stats import t

__author__ = 'zzt'


def ttest(a, mu):
    ave = mean(a)
    s = std(a, ddof=1)
    tv = (ave - mu) / s * sqrt(len(a))
    p = t.sf(abs(tv), len(a) - 1) * 2
    return [tv, p]


class Solution():
    def ttest_1samp(self, a, popmean):
        if len(a) == 0:
            return [None, None]
        return self.res(ttest(a, popmean))

    def res(self, a):
        return [round(x, 6) for x in a]


if __name__ == '__main__':
    rvs = [1, 2, 3]
    print(stats.ttest_1samp(rvs, 2))
    # print(ttest(rvs, 2))
    s = Solution()
    print(s.ttest_1samp(rvs, 2))
