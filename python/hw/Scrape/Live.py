# -*- coding:utf-8 -*-

import json
import requests
from practise.Scrape.CsvHelper import dict_to_csv

__author__ = 'zzt'


def find_live(game_id, period):
    # http://china.nba.com/wap/static/data/game/playbyplay_0041400311_4.json
    aim = 'http://china.nba.com/wap/static/data/game/playbyplay_' \
          + str(game_id) + \
          '_' \
          + str(period) + \
          '.json'

    game = requests.get(aim)
    game.encoding = 'utf-8'
    if game.status_code > 400:
        print('no suck game ' + str(game.status_code))
        return

    events = game.json()['payload']['playByPlays'][0]['events']
    id_ = 'live' + str(game_id)
    dict_to_csv(
        events[0], id_
    )
    for x in range(1, len(events)):
        dict_to_csv(
            events[x], id_, False, 'a'
        )


if __name__ == '__main__':
    playoff_date_code = '004'
    season = 14
    round_of = '{:03}'.format(3)
    rank = 1
    kth = 1

    period = 4

    find_live(playoff_date_code +
              str(season) + round_of +
              str(rank) + str(kth), period)
