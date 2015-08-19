import os
from learn_class.os.bitmap import bitMap

SECTOR = 512
BITS = 8
INT = 82
NODE_SZ = 128

__author__ = 'zzt'


class mkImg:
    # argument: ./disk.img, indoe number, block number, block size(in kB)
    def __init__(self, disk, ino, bno, bsize):
        self.dir = os.path.dirname(disk)
        self.disk = disk
        self.inode_num = ino
        self.block_num = bno
        self.bsize = bsize * 2 ** 10
        self.start = []
        # first size is super block
        self.size = [SECTOR, ino / BITS, bno / BITS, ino * NODE_SZ, bno * bsize]
        self.buf = bytearray(sum(self.size))

    '''
    fill the gap to meet a SECTOR
    :param size: size of original buffer
    :return: how many 0 is filled
    '''

    def fill_gap(self, size):
        gap = SECTOR - size % SECTOR
        if gap == SECTOR:
            gap = 0

        for x in range(gap):
            self.buf.append(0)

        return gap

    # def init_map(self):
    #     for x in range(self.size[0] + self.size[1]):
    #         self.buf.append(0)
    #
    # def init_inode_block(self):
    #     for x in range(self.size[2] + self.size[3]):
    #         self.buf.append(0)

    def get_area(self, start, x, unit):
        return start + x * unit

    def make_file(self):
        # apply for inode, block
        map1 = bitMap(self.buf, self.start[0], self.size[1])
        x = map1.apply_bit()
        inode_o = self.get_area(self.start[2], x, NODE_SZ)
        map2 = bitMap(self.buf, self.start[1], self.size[2])
        y = map2.apply_bit()
        block_o = self.get_area(self.start[3], y, self.bsize)
        # set block offset, type in inode
        return block_o

    def init_super(self, start):
        # inode map start after a sector of super start,
        # last start is useless
        for x in self.size:
            self.start.append(start + x)

        def int_to_bytes_bigendian(i):
            j = (2 ** BITS - 1) << (INT - BITS)
            l = []
            for x in range(INT / BITS):
                l.append(j & i)
                j >>= BITS

            return l

        def int_to_bytes_small_endian(i):
            j = (2 ** BITS - 1)
            l = []
            for x in range(INT / BITS):
                l.append(j & i)
                j <<= BITS

            return l

        for x in range(len(self.size) - 1):
            # TODO small endian?
            for b in int_to_bytes_small_endian(self.start[x]):
                self.buf[start] = b
                start += 1
            for b2 in int_to_bytes_small_endian(self.size[x + 1]):
                self.buf[start] = b2
                start += 1

    def make_img(self):
        disk_sz = os.path.getsize(self.disk)
        # although it is append at end, for they are all zero, so it's same
        super_start = self.fill_gap(disk_sz) + disk_sz
        assert super_start % SECTOR == 0
        print(super_start)
        self.init_super(super_start)
        # it is done in __init__
        # self.init_map()
        # self.init_inode_block()
        block_o = self.make_file()
        # write . .. in block
        with open(os.path.join(self.dir, 'harddisk.img'), 'wb') as img:
            img.write(bytearray(self.buf))


if __name__ == '__main__':
    a = mkImg('./', 2 ** 12, 2 ** 20, 1)
