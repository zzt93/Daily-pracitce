from math import sqrt
from statistic.check.util import average, s_2

__author__ = 'zzt'


def pearson_r(x, y):
    assert len(x) == len(y)
    x_bar = average(x)
    y_bar = average(y)
    s_x = sqrt(s_2(x))
    s_y = sqrt(s_2(y))
    tmp = 0.0
    for i in range(0, len(x)):
        tmp += ((x[i] - x_bar) * (y[i] - y_bar) / s_x / s_y)
    return tmp / (len(x) - 1)
