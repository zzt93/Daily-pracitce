__author__ = 'zzt'

import requests
import json
import pandas as pd

players = []
player_stats = {'name': None, 'avg_dribbles': None, 'avg_touch_time': None, 'avg_shot_distance': None,
                'avg_defender_distance': None}


def find_stats(season, game_id):
    # http://stats.nba.com/stats/boxscoreadvancedv2?EndPeriod=10&EndRange=28800&GameID=
    # 0020000654
    # &RangeType=2&Season=
    # 2000-01
    # &SeasonType=Regular+Season&StartPeriod=1&StartRange=0
    # '2014-15', 0021400160
    url = 'http://stats.nba.com/stats/boxscoretraditionalv2?EndPeriod=10&EndRange=28800&' \
          'GameID=' \
          + game_id + \
          '&RangeType=2&Season=' \
          + season + \
          '&SeasonType=Regular+Season&StartPeriod=1&StartRange=0'

    # Create Dict based on JSON response
    response = requests.get(url)
    players = response.json()['resultSets'][0]['rowSet']
    data = json.loads(response.text)

    # Create df from data and find averages
    headers = data['resultSets'][0]['headers']
    shot_data = data['resultSets'][0]['rowSet']
    df = pd.DataFrame(shot_data, columns=headers)
    avg_def = df['CLOSE_DEF_DIST'].mean(axis=1)
    avg_dribbles = df['DRIBBLES'].mean(axis=1)
    avg_shot_distance = df['SHOT_DIST'].mean(axis=1)
    avg_touch_time = df['TOUCH_TIME'].mean(axis=1)

    # add Averages to dictionary then to list
    player_stats['name'] = name
    player_stats['avg_defender_distance'] = avg_def
    player_stats['avg_shot_distance'] = avg_shot_distance
    player_stats['avg_touch_time'] = avg_touch_time
    player_stats['avg_dribbles'] = avg_dribbles
    players.append(player_stats.copy())

if __name__ == '__main__':
    dateCode = '002140'

    for x in range(0, 1230):
        num = x.__format__()
        find_stats(2014-15, )