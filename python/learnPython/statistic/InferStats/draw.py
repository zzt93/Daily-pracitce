from matplotlib.pyplot import scatter, savefig, hist, plot, figtext, show, ylabel, xlabel

__author__ = 'zzt'


def draw_scatter(x, y, area):
    # get input data
    print 'x is: ' + str(x)
    print 'y is: ' + str(y)
    colors = ('b', 'g', 'r', 'c', 'm', 'y', 'k', 'w')
    # the scatter chart of the data
    scatter(x, y, s=area, c=colors, alpha=0.6)


def draw_hist(x, bin, c='g'):
    print('data is : ' + str(x))
    # the histogram of the data
    hist(x, bins=bin, color=c, alpha=0.5)


def draw_line(x):
    plot(x)


def draw_curve(x, f, a, b):
    plot(x, f(x, a, b))


def finish(x_str, y_str, path=''):
    xlabel(x_str)
    ylabel(y_str)
    # show image
    savefig(path + '.png')


def add_text(x, y, s, font=15):
    figtext(x, y, s, fontsize=font)


if __name__ == '__main__':
    x = [1.0, 2.9, 3.8]
    draw_line(x)
    add_text(0.5, 0.3, 'asdfasdf')
    show()

    # finish('test_line')
