import csv

__author__ = 'zzt'


with open('some.csv', encoding='utf-8') as f:
    reader = csv.reader(f)
    for row in reader:
        print(row)
