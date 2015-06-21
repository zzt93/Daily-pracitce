from __future__ import division
from numpy import mean
from scipy.stats import f

__author__ = 'zzt'


def f_one(*args):
    a = list(args)
    for ai in a:
        if not ai:
            return [None, None]
    r = len(a[0])
    m = len(a)
    x_bar = mean(sum([], a))
    x_ib = []
    for x in a:
        x_ib.append(mean(x))
    assert len(x_ib) == m

    s_e = 0.0
    s_t = 0.0
    for i in range(m):
        for j in range(r):
            s_e += (a[i][j] - x_ib[i]) ** 2
            s_t += (a[i][j] - x_bar) ** 2
    s_a = s_t - s_e
    va = s_a / (m - 1)
    ve = s_e / (m * r - m)
    fv = va / ve
    p = f.sf(fv, dfn=m - 1, dfd=m * r - m)
    return [fv, p]


def res(a):
    return [round(x, 6) for x in a]


class Solution():
    def f_oneway(self, *args):
        r = f_one(*args)
        if r != [None, None]:
            return res(r)
        return r


if __name__ == '__main__':
    s = Solution()
    print(s.f_oneway([1, 2, 3], [2, 2, 3]))
    print(f_one([1, 2, 3], [2, 2, 3]))
