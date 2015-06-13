from hw_scrape.PIE import get_pie
from statistic.InferStats import draw
from statistic.InferStats.draw import draw_scatter

__author__ = 'zzt'

PATH = ''
REST_LIST = (0, 1, 2, 3, 4, 6)


def infer_rest_player(player_id, path=PATH):
    rest = get_pie(player_id)[1:]
    assert len(rest) == len(REST_LIST)
    # draw graph
    draw_scatter(REST_LIST, rest, 10)
    # TODO analysis

    draw.finish(path)


if __name__ == '__main__':
    infer_rest_player('203112')
