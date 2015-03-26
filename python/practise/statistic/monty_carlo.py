import random
import numpy as ny
'''
You can delete the MontyCarlo and write your Monty Carlo
'''

l = 2

def pin():
    cen = random.uniform(1, 9)
    # 0 <= s, e <= 10
    angle = random.uniform(0, 90)
    tmp = l/2 * math.cos(math.radians(angle));
    s = cen - tmp
    e = cen + tmp
    if e - s == 2:
        return 3
    elif 2 > e - s >= 1:
        return 2
    elif 1 > e - s:
        return 1


#this function may spend a little time to execute
def MontyCarlo():
    n = 1000000
    count = 0
    for i in range(n):
        count += pin()
        
    return 2 * float(n) / float(count) 

if __name__ == '__main__':  
    print MontyCarlo()

