__author__ = 'zzt'


class bitMap:
    USED = 1
    FREE = 0
    '''
    bits is start from l[i], having size bits all
    l is a bytearray, ie each element has 8 bits
    '''
    def __init__(self, l, i, sz):
        self.l = l
        self.start = i
        self.size = sz

    def first_val(self, value):
        for x in range(self.size):
            if self.is_val(x, value):
                return x

    def is_val(self, x, value):
        pass

    def set_val(self, x, value):
        pass

    def apply_bit(self):
        x = self.first_val(bitMap.FREE)
        self.set_val(x, bitMap.USED)
        return x

