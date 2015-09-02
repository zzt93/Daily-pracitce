__author__ = 'zzt'

class Meta(type):
    pass

class Cls(metaclass=Meta):
    pass


if __name__ == '__main__':
    a = Cls()
    # the object's type
    print(type(a))
    print(type(Meta))
    print(type(Cls))