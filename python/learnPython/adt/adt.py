__author__ = 'zzt'


def add(x, y):
    """

    :type x: int
    """
    return x + y


if __name__ == '__main__':
    # list
    sum1 = reduce(add, range(1, 100))
    sum2 = sum(range(1, 100))
    print sum1, sum2
    square = [x ** 2 for x in range(1, 10)]
    square2 = map(lambda r: r ** 2, range(1, 10))

    comb = [(x, y) for x in range(1, 4) for y in [1, 3, 5] if x != y]
    print(comb)
    del comb[0]
    print(comb)
    del comb[2:4]
    print(comb)
    print(square)

    # tuple
    t = 12, 23, 'sd', [1, 2]
    t[3][1] = 1
    print(t)

    # set
    num = {'asd', 'sdf', 'sdfs', 'sdfa'}
    print('asd' in num)
    square.extend(square2)
    print(set(square))

    a = set('asd fgrea')
    b = set('sgerga')
    print a, b
    print a - b

    a = {x for x in 'abracadabra' if x not in 'abc'}
    print(a)

    # map
    player = {'name': 'jack', 'id': 132, 'age': 20}
    print(player)

    # looping
    for i, v in enumerate(square):
        print i, v, ';',


    # condition
    print(square == square2)
    print(square is square2)



