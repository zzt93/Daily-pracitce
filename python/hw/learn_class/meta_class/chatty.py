__author__ = 'zzt'


class ChattyType(type):
    def __new__(cls, *args, **kwargs):
        print('allocate some memory')

    def __init__(self):
        print()