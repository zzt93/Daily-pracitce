from scipy import integrate

__author__ = 'zzt'

# -*- coding:utf-8 -*-
import math
import random


class Solution:
    def __init__(self):
        pass

    def f(self, x):
        return pow(math.e, -1.0 * x ** 2 / 2) / math.sqrt(2 * math.pi)

    def solve(self, a, b):
        assert b > a > 0
        v = b - a
        n = 200000
        s = 0.0
        for i in range(0, n):
            s += self.f(random.uniform(a, b))

        return v * s / n


if __name__ == '__main__':
    s = Solution()
    print(s.solve(0, 5))
    print(integrate.quad(
        lambda x: pow(math.e, -1.0 * x ** 2 / 2) / math.sqrt(2 * math.pi), 0, 5))
