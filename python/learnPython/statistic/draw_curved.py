__author__ = 'zzt'
import matplotlib.pyplot as plt
import numpy as np


def f(x):
    return [1 / (1 + 1 / b) for b in x]


if __name__ == '__main__':
    z = np.linspace(0.1, 5, 3001)

    plt.plot(z, f(z))
    plt.show()
