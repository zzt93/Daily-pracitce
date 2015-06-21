from __future__ import division

from numpy import mean
from math import sqrt

__author__ = 'zzt'

import requests
from scipy.stats import norm


def get_data():
    url = 'http://112.124.1.3:8060/getData/101.json'
    resp = requests.get(url)
    if resp.status_code >= 400:
        return []

    stats = resp.json()['data']
    return stats


def handle_data():
    stats = get_data()
    weeks = []
    for l in stats:
        if 25 < l[2] < 49:
            weeks.append(l[2])
        elif 5 < l[2] <= 10:
            weeks.append(l[2] * 4.33)
    return weeks


class Solution:
    def solve(self):
        weeks = handle_data()
        upper = norm.interval(0.95)[1]

        ave = mean(weeks)
        weeks_upper = 4 / sqrt(len(weeks)) * upper
        return [ave - weeks_upper, ave + weeks_upper]


if __name__ == '__main__':
    s = Solution()
    print(s.solve())
