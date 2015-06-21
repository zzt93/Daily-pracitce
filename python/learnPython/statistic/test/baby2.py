from __future__ import division
from scipy.stats import chi2_contingency, ks_2samp
from statistic.test.baby import get_data
from statistic.test.independ import indep

__author__ = 'zzt'


def add_data(m, a):
    if 5 < m <= 10:
        m *= 4.33

    if 25 < m <= 37:
        a[0] += 1
    elif 37 < m <= 40:
        a[1] += 1
    elif 40 < m < 49:
        a[2] += 1


def first_sec(stats):
    obs = [[0, 0, 0], [0, 0, 0]]
    for l in stats:
        if l[5] == 1:
            add_data(l[2], obs[0])
        else:
            add_data(l[2], obs[1])

    return chi2_contingency(obs)[0:2]
    # return indep(obs)

if __name__ == '__main__':
    print(first_sec(get_data()))
