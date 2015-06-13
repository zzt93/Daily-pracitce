from matplotlib.pyplot import scatter, savefig, hist, plot

__author__ = 'zzt'


def draw_scatter(x, y, area):
    # get input data
    print 'x is: ' + str(x)
    print 'y is: ' + str(y)
    colors = ('b', 'g', 'r', 'c', 'm', 'y', 'k', 'w')
    # the scatter chart of the data
    scatter(x, y, s=area, c=colors, alpha=0.6)


def draw_hist(x, bin):
    print('data is : ' + str(x))
    # the histogram of the data
    hist(x, bins=bin, color='g', alpha=0.5)


def draw_line(x):
    plot(x)


def finish(path=''):
    # show image
    savefig(path + '.png')
