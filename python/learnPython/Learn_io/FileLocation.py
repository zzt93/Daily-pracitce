__author__ = 'zzt'

if __name__ == '__main__':
    l = ['test', 'is', 'location']
    # with open('test', 'w') as f:
    #     for s in l:
    #         f.write(s + '\n')

    with open('./test', 'w') as f:
        for s in l:
            f.write(s + '\n')

    # with open('../test', 'w') as f:
    #     for s in l:
    #         f.write(s + '\n')

    # with open('/home/zzt/t', 'w') as f:
    #     for s in l:
    #         f.write(s + '\n')
