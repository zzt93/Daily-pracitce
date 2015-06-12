# -*- coding:utf-8 -*-
from importlib import reload

import requests
import sys

from hw_scrape import CsvHelper
from hw_scrape.Games import REGULAR_CODE, PLAY_OFF

__author__ = 'zzt'

PATH = '/home/zzt/hw2l/trunk/NBADataAnalysisSystem/IterationThreeData/matches/live/'


def find_live(game_id, period):
    # http://china.nba.com/wap/static/data/game/playbyplay_0041400311_4.json
    aim = 'http://china.nba.com/wap/static/data/game/playbyplay_' \
          + str(game_id) + \
          '_' \
          + str(period) + \
          '.json'

    game = requests.get(aim)
    game.encoding = 'utf-8'
    if game.status_code >= 400:
        print('no suck game ' + str(game.status_code))
        return False
    try:
        events = game.json()['payload']['playByPlays'][0]['events']
        file_id = PATH + 'live' + str(game_id) + '_' + str(period)

        # CsvHelper.dict_to_csv_stream(events[0])
        # for x in range(1, len(events)):
        #     CsvHelper.dict_to_csv_stream(
        #         events[x], False
        #     )
        CsvHelper.dict_to_csv(events[0], file_id)
        for x in range(1, len(events)):
            CsvHelper.dict_to_csv(
                events[x], file_id, False, mode='a'
            )

    except (ValueError, IndexError):
        print(aim)

    return True


def regular(season):
    for x in range(1, 1230 + 1):
        num = '{:05}'.format(x)
        match_live(REGULAR_CODE + str(season) + num)


def match_live(code):
    for p in range(1, 5):
        find_live(code, p)

    for p in range(5, 11):
        n = find_live(code, p)
        if not n:
            return


def playoff(season):
    for round in range(2, 4 + 1):
        r = '{:03}'.format(round)
        for rank in range(0, 16 // (2 ** round)):
            for k in range(1, 7 + 1):
                match_live(PLAY_OFF + str(season) + r + str(rank) + str(k))


def all_live(season):
    # regular(season)
    playoff(season)


if __name__ == '__main__':
    # playoff_date_code = '004'
    # season = 14
    # round_of = '{:03}'.format(3)
    # rank = 0
    # kth = 1
    #
    # period = int(sys.argv[1])
    #
    # find_live(playoff_date_code +
    #           str(season) + round_of +
    #           str(rank) + str(kth), period)
    all_live('14')
