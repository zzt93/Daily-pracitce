import csv
import sys

__author__ = 'zzt'

PATH = ''  # '/home/zzt/hw2l/trunk/NBADataAnalysisSystem/IterationThreeData/matches/pre_season/'


def dict_to_csv(stats, filename, has_header=True, mode='w'):
    with open(PATH + filename, mode, encoding='utf-8') as fp:
        w = csv.DictWriter(fp, sorted(stats.keys()))
        if has_header:
            w.writeheader()
        w.writerow(stats)


def dict_to_csv_stream(stats, has_header=True):
    w = csv.DictWriter(sys.stdout, sorted(stats.keys()))
    if has_header:
        w.writeheader()
    w.writerow(stats)


def list2d_to_csv(stats, filename):
    with open(PATH + filename, 'w') as f:
        writer = csv.writer(f)
        writer.writerows(stats)


def dict_to_list2d(stats):
    l = [stats['headers']]
    l.extend(stats['rowSet'])
    return l


if __name__ == '__main__':
    d = {'shape': 4139, 'jack': 4098, 'guido': 4127}
    dict_to_csv(d, 'test')
