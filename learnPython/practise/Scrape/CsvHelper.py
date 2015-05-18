import csv

__author__ = 'zzt'


def dict_to_csv(stats, name):
    with open(name, 'w') as fp:
        w = csv.DictWriter(fp, stats.keys())
        w.writeheader()
        w.writerow(stats)


def list2d_to_csv(stats, name):
    with open(name, 'w') as f:
        writer = csv.writer(f)
        writer.writerows(stats)


if __name__ == '__main__':
    l = [[1, 2, 3], [11, 22, 33], [111, 222, 333]]
    list2d_to_csv(l, 'test')
