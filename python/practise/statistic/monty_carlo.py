__author__ = 'zzt'
# -*- coding:utf-8 -*-

import random
import math

'''
LCGs should not be used for applications where high-quality randomness is critical.
For example, it is not suitable for
a Monte Carlo simulation because of the serial correlation (among other things)
'''

l = 2


def pin():
    cen = random.uniform(1, 9)
    # so 0 <= s, e <= 10
    angle = random.uniform(0, 90)
    tmp = l / 2 * math.cos(math.radians(angle))
    s = cen - tmp
    e = cen + tmp
    le = math.floor(e)
    ls = math.floor(s)

    if le == e and ls == s:
        if e - s == 2:
            return 3
        elif e - s == 1:
            return 2
        elif e - s == 0:
            return 1

    le_ls = le - ls
    if le_ls == 2:
        return 2
    elif le_ls == 1:
        return 1
    elif le_ls == 0:
        return 0


# this function may spend a little time to execute
def montyCarlo():
    n = 1000000
    count = 0
    for i in range(n):
        count += pin()

    return 4 * float(n) / float(count)


if __name__ == '__main__':
    print montyCarlo()
