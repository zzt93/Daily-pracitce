__author__ = 'zzt'
# -*- coding:utf-8 -*-

import sys
import numpy as np
import matplotlib.pyplot as plt


def get_data():
    # example data
    mu = 100  # mean of distribution
    sigma = 15  # standard deviation of distribution
    x = mu + sigma * np.random.randn(50)
    return x


def get_scatter_area():
    # 0 to 30 point radiuses
    return np.pi * (30 * np.random.rand(50)) ** 2


# you can write your code here
def draw(img_path):
    # get input data
    x = get_data()
    y = get_data()
    area = get_scatter_area()
    print 'x is: ' + str(x)
    print 'y is: ' + str(y)
    colors = ('b', 'g', 'r', 'c', 'm', 'y', 'k', 'w')
    # the scatter chart of the data
    plt.scatter(x, y, s=area, c=colors, alpha=0.4)
    # show image
    plt.savefig(img_path)

# the code should not be changed
if __name__ == '__main__':
    if len(sys.argv) == 1:
        print 'error'
    else:
        # the path of image to show
        IMG_PATH = sys.argv[1] + 'fig.jpg'
        draw(IMG_PATH)