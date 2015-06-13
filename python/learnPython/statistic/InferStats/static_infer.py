from __future__ import division
from math import sqrt
from hw_scrape.PIE import get_overall_pie
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
    infer_overall()
