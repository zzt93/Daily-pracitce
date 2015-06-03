from enum import Enum

__author__ = 'zzt'


class GameType(Enum):
    traditional = 1
    advanced = 2
    misc = 3
    scoring = 4
    usage = 5


class PlayerType(Enum):
    Base = 1
    Advanced = 2
    Misc = 3
    Scoring = 4
    Usage = 5


class SeasonType(Enum):
    regular = 'Regular+Season'
    playoff = 'playoffs'

class TeamType(Enum):
    Base = 1

if __name__ == '__main__':
    for t in GameType:
        print(t)
