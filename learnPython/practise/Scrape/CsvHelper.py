import csv

__author__ = 'zzt'


def dict_to_csv(stats, filename, has_header=True, mode='w'):
    with open(filename, mode, encoding='utf-8') as fp:
        w = csv.DictWriter(fp, sorted(stats.keys()))
        if has_header:
            w.writeheader()
        w.writerow(stats)


def list2d_to_csv(stats, filename):
    with open(filename, 'w') as f:
        writer = csv.writer(f)
        writer.writerows(stats)


if __name__ == '__main__':
    d = {'sape': 4139, 'jack': 4098, 'guido': 4127};
    dict_to_csv(d, 'test')
