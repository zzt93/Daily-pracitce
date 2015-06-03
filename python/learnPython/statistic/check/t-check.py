from math import sqrt
from statistic.check.util import average, s

__author__ = 'zzt'


def t_check(l, mu):
    aver = average(l)
    n = len(l)
    ss = s(l)
    t = (aver - mu) * sqrt(n) / ss
