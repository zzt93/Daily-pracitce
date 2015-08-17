import os

__author__ = 'zzt'

# argument: ./disk.img, indoe number, block number, block size
class mkimg:
    def __init__(self, path, ino, bno, bsize):
        self.disk = os.path.dirname(path)
        self.inode_num = ino
        self.block_num = bno
        self.bsize = bsize
        self.img = os.path.join(self.disk, 'harddisk')

    def calcu_gap(self):
        disk = os.path.getsize(self.disk)
