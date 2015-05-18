from practise.Scrape import CsvHelper
from practise.Scrape.DetailType import DetailType
import requests
import json
import csv

SEASON_SPLIT = '-'

SEASON_HEAD = '20'

__author__ = 'zzt'


def find_detail(season, game_id, detail_type):
    # http://stats.nba.com/stats/boxscoreadvancedv2?EndPeriod=10&EndRange=28800&GameID=
    # 0020000654
    # &RangeType=2&Season=
    # 2000-01
    # &SeasonType=Regular+Season&StartPeriod=1&StartRange=0
    # '2014-15', 0021400160
    detail = 'http://stats.nba.com/stats/boxscore' \
             + str(detail_type.name) + \
             'v2?EndPeriod=10&EndRange=28800&' \
             'GameID=' \
             + str(game_id) + \
             '&RangeType=2&Season=' \
             + str(season) + \
             '&SeasonType=Regular+Season&StartPeriod=1&StartRange=0'

    detail_resp = requests.get(detail)

    player_stats = detail_resp.json()['resultSets'][0]
    team_stats = detail_resp.json()['resultSets'][1]

    CsvHelper.list2d_to_csv(
        dict_to_list2d(player_stats), 'player_' + str(game_id)
    )
    CsvHelper.list2d_to_csv(
        dict_to_list2d(team_stats), 'team_' + str(game_id)
    )

    # with open('player' + str(game_id), 'w') as fp:
    #     json.dump(player_stats, fp)
    #
    # with open('team' + str(game_id), 'w') as fp:
    #     json.dump(team_stats, fp)


def find_summary(game_id):

    # 0041400160
    summary = 'http://stats.nba.com/stats/boxscoresummaryv2?GameID=' \
              + str(game_id)

    summary_resp = requests.get(summary)
    game_summary = summary_resp.json()['resultSets'][0]

    CsvHelper.list2d_to_csv(
        dict_to_list2d(game_summary), 'summary_' + str(game_id)
    )
    # with open('summary' + str(game_id), 'w') as fp:
    #     json.dump(game_summary, fp)

def dict_to_list2d(stats):
    l = [stats['headers']]
    l.extend(stats['rowSet'])
    return l


if __name__ == '__main__':
    regular_date_code = '002'
    playoff_date_code = '004'

    season = 14

    find_detail(SEASON_HEAD + str(season) + SEASON_SPLIT + str(season+1),
                regular_date_code + str(season) + '00001',
                DetailType.traditional)


    # get regular game -- from 1 to 1230
    # for x in range(1, 1230):
    # num = '{:05}'.format(x)
    # print(num)
    # find_detail(2014-15, dateCode + num, DetailType.traditional)
