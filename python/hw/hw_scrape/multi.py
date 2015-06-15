from hw_scrape.Games import regular, playoff
from multiprocessing import Pool

__author__ = 'zzt'


def aim(i):
    regular(i)
    playoff(i)


def multi_process():
    p = Pool(5)
    r = []
    for i in range(0, 5):
        r.append(i)

    p.map(aim, r)

if __name__ == '__main__':
    multi_process()