import os
import struct

SECTOR = 512
BITS = 8
NODE_SZ = 128

__author__ = 'zzt'


class mkimg:
    # argument: ./disk.img, indoe number, block number, block size(in kB)
    def __init__(self, disk, ino, bno, bsize):
        self.dir = os.path.dirname(disk)
        self.disk = disk
        self.inode_num = ino
        self.block_num = bno
        self.bsize = bsize
        self.buf = []
        self.start = []
        self.size = [ino / BITS, bno / BITS, ino * NODE_SZ, bno * bsize * 2 ** 10]

    def fill_gap(self):
        disk_sz = os.path.getsize(self.disk)
        gap = SECTOR - disk_sz % SECTOR
        if gap == SECTOR:
            gap = 0

        for x in range(gap):
            self.buf.append(0)

        return gap + disk_sz

    def init_map(self):
        for x in range(self.inode_num / BITS + self.inode_num / BITS):
            self.buf.append(0)

    def init_inode_block(self):
        for x in range(self.inode_num * NODE_SZ +
                                               self.block_num * self.bsize * 2 ** 10):
            self.buf.append(0)

    def make_file(self):
        map1 = self.start[0]
        # set inode,

    def make_img(self):
        super_start = self.fill_gap()
        print(super_start)
        self.init_super(super_start)
        self.init_map()
        self.init_inode_block()
        self.make_file()
        with open(os.path.join(self.dir, 'harddisk.img'), 'wb') as img:
            img.write(bytearray(self.buf))

    def init_super(self, start):
        for x in self.size:
            self.start.append(start + x)

        for x in range(len(self.size)):
            # TODO
            self.buf.append(self.start[x])
            self.buf.append(self.size[x])


if __name__ == '__main__':
    a = mkimg('./', 2 ** 12, 2 ** 20, 1)
