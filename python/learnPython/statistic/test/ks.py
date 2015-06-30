from __future__ import division
import math

__author__ = 'zzt'

from bisect import bisect_right


def binary_search(a, x, lo=0, hi=None):
    hi = hi if hi is not None else len(a)
    pos = bisect_right(a, x, lo, hi)
    return pos


# def search(l, x):
#     for i, lv in enumerate(l):
#         if lv > x:
#             return i
#     return len(l)

def find_sorted(l, ll):
    start = 0
    pos = []
    for x in ll:
        start = binary_search(l, x, start)
        pos.append(start)
    return pos


def ks2(d1, d2):
    # d1 = list(d1)
    # d2 = list(d2)
    data_all = d1 + d2
    n1 = len(d1)
    n2 = len(d2)
    data_all.sort()
    d1.sort()
    d2.sort()
    f1 = find_sorted(d1, data_all)
    f2 = find_sorted(d2, data_all)
    q1 = [f / n1 for f in f1]
    q2 = [f / n2 for f in f2]
    r = [abs(qv1 - qv2) for qv1, qv2 in zip(q1, q2)]
    return max(r)


class Solution():
    def ks_2samp(self, data1, data2):
        return ks2(data1, data2)


if __name__ == '__main__':
    l = [1.0, 2.0, 3.0]
    ll = [2.0, 2.0, 3.0]
    # l.extend(ll)

    # print(ks_2samp(l, ll))
    # print(find_sorted(sorted(ll), sorted(l)))
    # print(ks2(l, ll))
    print(Solution().ks_2samp(l, ll))
