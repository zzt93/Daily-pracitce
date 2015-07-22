from __future__ import division
import math
import numpy as np
from sys import argv
from statistic.InferStats.PIE import get_pie
from statistic.InferStats import pearson_r
from statistic.InferStats import draw
from statistic.InferStats.draw import draw_scatter, draw_curve, add_text
from statistic.check.util import average

THRESHOLD = 0.7

__author__ = 'zzt'

PATH = '../'
REST_LIST = [0.0000001, 1, 2, 3, 4, 5, 6]

functions = {
    0: lambda x, a, b: [a + b * i for i in x],
    1: lambda x, a, b: [1 / (a + b / i) for i in x],
    2: lambda x, a, b: [math.pow(math.e, a) * math.pow(math.e, b * i) for i in x],
    3: lambda x, a, b: [a + b * math.log(i) for i in x],
    4: lambda x, a, b: [math.pow(math.e, a) * math.pow(i, b) for i in x]
}


def min_2(x, y):
    assert len(x) == len(y)
    x_bar = average(x)
    y_bar = average(y)
    lxy_1 = 0.0
    for i in range(0, len(x)):
        lxy_1 += (x[i] * y[i])
    lxy = lxy_1 - sum(x) * sum(y) / len(x)
    l_xx = sum(a ** 2 for a in x) - sum(x) ** 2 / len(x)
    b = lxy / l_xx
    a = y_bar - b * x_bar
    r = pearson_r.pearson_r(x, y)
    print('{}: y = {} + {}*x'.format(r, a, b))
    return [r, a, b]


def get_f(a, b, i):
    def f(x):
        return functions[i](x, a, b)

    return f


def get_infer(a, b, i):
    f = get_f(a, b, i)
    r = f(range(0, len(REST_LIST)))

    index = r.index(max(r))
    if index != len(REST_LIST) - 1:
        return 'The player should rest {} day'.format(index)
    else:
        return 'The player should rest {}+ day'.format(index)


def different_model(x, y):
    r = [0] * 10
    a = [1] * 10
    b = [1] * 10
    # linear
    r[0], a[0], b[0] = min_2(x, y)
    # 1/x
    r[1], a[1], b[1] = min_2([1 / ax for ax in x], [1 / bx for bx in y])
    # lnx
    r[3], a[3], b[3] = min_2([math.log(ax) for ax in x], y)

    try:
        # e^x
        r[2], a[2], b[2] = min_2(x, [math.log(bx) for bx in y])
        # x^b
        for i in range(0, len(REST_LIST)):
            r[4 + i], a[4 + i], b[4 + i] = min_2([math.log(ax - i) for ax in x], [math.log(bx) for bx in y])
    except:
        pass

    r_ = [abs(t) for t in r]
    i = r_.index(max(r_))
    if max(r_) < THRESHOLD:
        return 'The player is too unsteady'
    else:
        draw_curve(np.linspace(0, 6, 3001), functions[i], a[i], b[i])
        return get_infer(a[1], b[i], i)


def infer_rest_player(player_id, path=PATH):
    rest = get_pie(player_id)[1:]
    assert len(rest) == len(REST_LIST)
    # draw graph
    tmp = []
    final = []
    for i in range(0, len(rest)):
        if rest[i] != 0:
            final.append(rest[i])
            tmp.append(REST_LIST[i])

    draw_scatter(tmp, final, 10)
    if len(tmp) < 3:
        return
    s = different_model(tmp, final)
    add_text(0.3, 0.15, s)
    draw.finish('Day(s)', 'PIE', path + player_id)


if __name__ == '__main__':
    if len(argv) == 2:
        infer_rest_player(argv[1])

        # l = [116.5, 120.8, 124.4, 125.5, 131.7, 136.2, 138.7, 140.2]
        # y = [255.7, 263.3, 275.4, 278.4, 296.7, 309.3, 315.8, 318.8]
        # draw_scatter(y, l, 10)
        # different_model(y, l)
        # draw.finish('test.txt')
