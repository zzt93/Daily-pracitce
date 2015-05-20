# -*- coding:utf-8 -*-
import sys
import numpy as np
import matplotlib.pyplot as plt


def get_data():
    # example data
    mu = 100  # mean of distribution
    sigma = 15  # standard deviation of distribution
    x = mu + sigma * np.random.randn(10000)
    return x


# you can write your code here
def draw(IMG_PATH):
    # get input data
    x = get_data()
    print('data is : ' + str(x))
    # the histogram of the data
    plt.hist(x, bins=50, color='g', alpha=0.5)
    # show image
    plt.savefig(IMG_PATH)

# the code should not be changed
if __name__ == '__main__':
    if len(sys.argv) == 1:
        print('error')
    else:
        # the path of image to show
        IMG_PATH = sys.argv[1] + 'fig.jpg'
        draw(IMG_PATH)