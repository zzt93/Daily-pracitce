from math import sqrt
from statistic.check.util import average, s_2
from scipy.stats import t

__author__ = 'zzt'


def t_fun():
    # accumulate from -infinity to 3.077
    res = t.cdf(3.0777, df=1)
    print(res)
    # probability of middle
    a, b = t.interval(0.95, 1)
    print(a, b)


def t_check(l, mu, alpha=0.05):
    aver = average(l)
    n = len(l)
    ss = s_2(l)
    tt = (aver - mu) * sqrt(n) / sqrt(ss)
    l, r = t.interval(1 - alpha, n - 1)
    if tt > r or tt < l:
        print('reject')
    else:
        print('accept')


if __name__ == '__main__':
    l = [1, 2]
