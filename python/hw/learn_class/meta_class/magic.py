__author__ = 'zzt'

# magic methods: they allow the programmer to override
# behavior for various operators and behavior of objects
class Fun:
    def __call__(self, *args, **kwargs):
        print('class Fun is called')


if __name__ == '__main__':
    f = Fun()
    f()
