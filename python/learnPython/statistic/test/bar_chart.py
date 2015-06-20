# -*- coding:utf-8 -*-
import sys
import numpy as np
import matplotlib.pyplot as plt


def get_data(mu):
    # example data
    # mean of distribution
    sigma = 15  # standard deviation of distribution
    x = mu + sigma * np.random.randn(10000)
    return x


# you can write your coding here
def draw(img_path):
    # get input data
    x = get_data(100)
    y = get_data(200)
    # print('data is : ' + str(x))
    # the histogram of the data
    plt.hist(x, bins=50, color='g', alpha=0.5)
    plt.hist(y, bins=50, color='r', alpha=0.5)
    # show image
    plt.savefig(img_path)

# the coding should not be changed
if __name__ == '__main__':
    if len(sys.argv) == 1:
        print('error')
    else:
        # the path of image to show
        IMG_PATH = sys.argv[1] + 'fig.jpg'
        draw(IMG_PATH)
