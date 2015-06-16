from hw_scrape.Games import regular, playoff, old_playoff
from multiprocessing import Pool

__author__ = 'zzt'


def aim(i):
    regular(i)
    # playoff(i)
    old_playoff(i)


def multi_process():
    r = []
    for i in range(85, 95):
        r.append(i)

    p = Pool(len(r))
    p.map(aim, r)


if __name__ == '__main__':
    multi_process()
