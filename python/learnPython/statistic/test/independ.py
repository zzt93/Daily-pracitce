from __future__ import division
from scipy.stats import chi2

__author__ = 'zzt'


def indep(a):
    row = len(a)
    col = len(a[0])
    n = sum(sum(a, []))
    if row == 1:
        return [0, None]

    sum_r = []
    for r in a:
        sum_r.append(sum(r))

    sum_c = []
    for r in zip(*a):
        sum_c.append(sum(r))

    chi_2 = 0.0
    for ri, r in enumerate(a):
        for ci, c in enumerate(r):
            tij = sum_r[ri] * sum_c[ci] / n
            chi_2 += (c - tij) ** 2 / tij

    # c = sqrt(chi2 / (chi2 + n))
    p = chi2.sf(chi_2, df=(row - 1) * (col - 1))
    return [chi_2, p]

if __name__ == '__main__':
    a = [[1, 2, 3], [2, 2, 3]]
    print(indep(a))