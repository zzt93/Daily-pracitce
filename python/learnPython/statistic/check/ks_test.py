import numpy as np
from numpy import var, std
from scipy.stats import stats
from statistic.check.util import s_2, sigma_2

__author__ = 'zzt'

if __name__ == '__main__':
    l = [420, 500, 920, 1380, 1510, 1650, 1760, 2100, 2300, 2350]
    print(stats.kstest(l, 'expon', [1500.0]))
    x = np.linspace(-15, 15, 9)
    print(std(x)**2)
    print(var(x))
    print(s_2(x))
    print(sigma_2(x))
    print(stats.kstest(x, 'norm', [0, 9]))
