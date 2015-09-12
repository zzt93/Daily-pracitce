from requests import get
import sys

__author__ = 'zzt'


def download(url, name='../resource/res'):
    r = get(url)
    print(r.status_code)
    with open(name, 'wb') as f:
        f.write(bytes(r.text, 'utf-8'))


if __name__ == '__main__':
    if len(sys.argv) == 3:
        download(sys.argv[1], sys.argv[2])
    else:
        print('python download.py url filename')
