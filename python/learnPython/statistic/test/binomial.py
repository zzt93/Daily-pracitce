__author__ = 'zzt'
# -*- coding:utf-8 -*-
import sys
import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import binom as B


def binom_pmf(img_path):
    rv = B(10, 0.5)  # 10 independent trials, probability of success is 0.5
    x = np.arange(0, 11, 1)  # return evenly spaced values within 1 interval between [0,11)
    y = rv.pmf(x)  # probability mass function

    print y
    plt.bar(x, y, width=0.6, color='grey')  # make bar chart
    plt.savefig(img_path + 'fig.png')

# the coding should not be changed
if __name__ == '__main__':
    if len(sys.argv) == 1:
        print 'error'
    else:
        # the path of image to show
        IMG_PATH = sys.argv[1]
        binom_pmf(IMG_PATH)
