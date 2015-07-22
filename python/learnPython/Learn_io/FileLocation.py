__author__ = 'zzt'

if __name__ == '__main__':
    l = ['test.txt', 'is', 'location']
    # with open('test.txt', 'w') as f:
    #     for s in l:
    #         f.write(s + '\n')

    with open('./test.txt', 'w') as f:
        for s in l:
            f.write(s + '\n')

    # with open('../test.txt', 'w') as f:
    #     for s in l:
    #         f.write(s + '\n')

    # with open('/home/zzt/t', 'w') as f:
    #     for s in l:
    #         f.write(s + '\n')
