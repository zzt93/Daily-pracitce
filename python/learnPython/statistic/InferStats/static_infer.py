from __future__ import division
from math import sqrt
from numpy.ma import std
from scipy.stats.stats import kstest
from statistic.InferStats.PIE import get_overall_pie
from statistic.InferStats.draw import draw_hist, finish
from statistic.check.util import average, s_2
from scipy.stats import t

BIN = 10

__author__ = 'zzt'


# the sigma1 -- starter ; sigma2 -- non-starter
# the H0: sigma1**2 < sigma2**2
def infer_by_chi(l1, l2, alpha=0.01):
    ss1 = s_2(l1)
    ss2 = s_2(l2)
    fv = ss1 / ss2
    from scipy.stats import f

    res = f.ppf(1 - alpha, len(l1) - 1, len(l2) - 1)
    print('res {} fv {}'.format(res, fv))
    if res > fv:
        print('sigma1 <= sigma2')
    else:
        print('sigma1 > sigma2')


# the H0: mu1 >= mu2, H1: mu1 < mu2
def infer_by_t(l1, l2, alpha=0.01, sigma=0):
    n1 = len(l1)
    n2 = len(l2)
    x_aver = average(l1)
    y_aver = average(l2)
    s_1sq = s_2(l1)
    s_2sq = s_2(l2)
    s_w2 = ((n1 - 1) * s_1sq + (n2 - 1) * s_2sq) / (n1 + n2 - 2)

    tv = ((x_aver - y_aver) - sigma) / sqrt(s_w2 * (1 / n1 + 1 / n2))
    t_a = t.ppf(alpha, n1 + n2 - 2)
    print('tv {} t_a {}'.format(tv, t_a))
    if tv < t_a:
        print('mu1 < mu2')
    else:
        print('mu1 >= mu2')


def infer_ks_test_goodness(l1):
    # l = np.histogram(l1)
    # n = len(l)
    mean = average(l1)
    sigma = std(l1)
    res = kstest(l1, 'norm', [mean, sigma])
    if res[1] < 0.01:
        print('reject')
    else:
        print('accept')
    # print(res)


def infer_overall():
    l = get_overall_pie()
    draw_hist(l[0], BIN)
    draw_hist(l[1], BIN)
    finish('./static_infer')
    infer_by_t(l[1], l[0])
    infer_by_chi(l[1], l[0])


if __name__ == '__main__':
    # l1 = [78.1, 72.4, 76.2, 74.3, 77.4, 78.4, 76.0, 75.5, 76.7, 77.3]
    # l2 = [79.1, 81.0, 77.3, 79.1, 80.0, 79.1, 79.1, 77.3, 80.2, 82.1]
    # infer_by_t(l1, l2, 0.05)
    # infer_by_chi(l1, l2, 0.05)
    # infer_overall()
    l0 = [0.083, 0.068, 0.156, 0.111, 0.095, 0.109, 0.089, 0.064, 0.11, 0.098, 0.092, 0.071, 0.13, 0.113, 0.143, 0.071,
          0.085, 0.138, 0.065, 0.099, 0.115, 0.141, 0.087, 0.12, 0.124, 0.18, 0.1, 0.178, 0.192, 0.115, 0.103, 0.112,
          0.111, 0.125, 0.156, 0.081, 0.097, 0.118, 0.116, 0.138, 0.062, 0.126, 0.151, 0.169, 0.131, 0.076, 0.133,
          0.104, 0.112, 0.095, 0.146, 0.184, 0.113, 0.139, 0.094, 0.108, 0.097, 0.108, 0.071, 0.145, 0.107, 0.128,
          0.171, 0.142, 0.093, 0.101, 0.068, 0.132, 0.124, 0.11, 0.129, 0.097, 0.125, 0.072, 0.084, 0.148, 0.137, 0.13,
          0.089, 0.135, 0.126, 0.1, 0.081, 0.065, 0.116, 0.13, 0.15, 0.107, 0.097, 0.098, 0.111, 0.108, 0.133, 0.099,
          0.112, 0.102, 0.105, 0.173, 0.09, 0.101, 0.104, 0.143, 0.096, 0.06, 0.108, 0.106, 0.066, 0.048, 0.083, 0.12,
          0.133, 0.072, 0.126, 0.093, 0.11, 0.134, 0.146, 0.141, 0.121, 0.151, 0.121, 0.187, 0.081, 0.106, 0.097, 0.1,
          0.127]
    l1 = [0.089, 0.135, 0.133, 0.107, 0.091, 0.071, 0.09, 0.079, 0.053, 0.067, 0.1, 0.096, 0.072, 0.096, 0.102, 0.103,
          0.09, 0.106, 0.068, 0.082, 0.079, 0.095, 0.07, 0.073, 0.063, 0.106, 0.115, 0.088, 0.092, 0.086, 0.065, 0.084,
          0.094, 0.058, 0.101, 0.076, 0.078, 0.07, 0.103, 0.092, 0.052, 0.121, 0.066, 0.08, 0.052, 0.095, 0.058, 0.091,
          0.081, 0.062, 0.075, 0.1, 0.113, 0.16, 0.086, 0.073, 0.047, 0.093, 0.052, 0.05, 0.074, 0.092, 0.017, 0.022,
          0.068, 0.112, 0.1, 0.041, 0.079, 0.119, 0.09, 0.072, 0.11, 0.087, 0.074, 0.1, 0.115, 0.056, 0.092, 0.11,
          0.124, 0.111, 0.096, 0.078, 0.121, 0.116, 0.093, 0.098, 0.1, 0.101, 0.073, 0.058, 0.094, 0.079, 0.067, 0.087,
          0.105, 0.082, 0.13, 0.06, 0.091, 0.092, 0.081, 0.094, 0.099, 0.051, 0.084, 0.057, 0.08, 0.093, 0.086, 0.073,
          0.064, 0.086, 0.113, 0.05, 0.062, 0.113, 0.13, 0.104, 0.106, 0.086, 0.093, 0.076, 0.097, 0.07, 0.117, 0.043,
          0.131, 0.077, 0.129, 0.07, 0.097, 0.102, 0.069, 0.074, 0.112, 0.086, 0.104, 0.062, 0.063, 0.082, 0.117, 0.128,
          0.09]
    # draw_hist(l0, BIN)
    # draw_hist(l1, BIN, 'b')
    # finish('./static_infer')
    infer_ks_test_goodness(l1)
