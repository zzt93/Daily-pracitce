from __future__ import division
from numpy import mean
from statistic.test.baby import get_data

__author__ = 'zzt'


def l(x, y):
    s_xy = 0.0
    for i in range(len(x)):
        s_xy += (x[i] * y[i])
    return s_xy - sum(x) * sum(y) / len(x)


def age(stats):
    x = []
    y = []
    for s in stats:
        x.append(s[-2])
        y.append(s[4])
    lxy = l(x, y)
    lxx = l(x, x)
    b = lxy / lxx
    ybar = mean(y)
    xbar = mean(x)
    a = ybar - b * xbar
    # difference
    st = sum([(yi - ybar) ** 2 for yi in y])
    yesit = [a + b * xi for xi in x]
    sr = sum([(ye - ybar) ** 2 for ye in yesit])
    return [a, b, sr / st]


if __name__ == '__main__':
    print(age(get_data()))
