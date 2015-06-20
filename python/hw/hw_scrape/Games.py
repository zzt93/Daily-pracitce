import requests

from hw_scrape import CsvHelper
from hw_scrape.CsvHelper import dict_to_list2d
from hw_scrape.ParameterType import GameType

UNDER = '_'

PLAY_OFF = '004'

REGULAR_CODE = '002'

PRE_SEASON = '001'

SEASON_SPLIT = '-'

SEASON_HEAD = '19'

__author__ = 'zzt'

PATH = '/home/zzt/'


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
    if detail_resp.status_code >= 400:
        print('no such game ' + str(detail_resp.status_code))
        return False

    try:
        player_stats = detail_resp.json()['resultSets'][0]
        team_stats = detail_resp.json()['resultSets'][1]

        from hw_scrape import CsvHelper

        CsvHelper.list2d_to_csv(
            dict_to_list2d(player_stats), PATH + 'player_' + str(game_id) + UNDER + str(detail_type.name)
        )
        CsvHelper.list2d_to_csv(
            dict_to_list2d(team_stats), PATH + 'team_' + str(game_id) + UNDER + str(detail_type.name)
        )

    except (KeyError, IndexError, TypeError):
        print(detail)

    return True

    # with open('player' + str(game_id), 'w') as fp:
    #     json.dump(player_stats, fp)
    #
    # with open('team' + str(game_id), 'w') as fp:
    #     json.dump(team_stats, fp)


def find_summary(game_id):
    # 0041400161
    summary = 'http://stats.nba.com/stats/boxscoresummaryv2?GameID=' \
              + str(game_id)

    summary_resp = requests.get(summary)
    if summary_resp.status_code >= 400:
        print('no such game ' + str(summary_resp.status_code))
        return False

    try:
        home_team_id = summary_resp.json()['resultSets'][0]['rowSet'][0][6]
        line_score = summary_resp.json()['resultSets'][5]
        line_score['headers'].append('IS_HOME')
        is_home = (line_score['rowSet'][0][3] == home_team_id)

        line_score['rowSet'][0].append(is_home)
        line_score['rowSet'][1].append(not is_home)

        # CsvHelper.list2d_to_csv(
        #     dict_to_list2d(game_summary), 'summary_' + str(game_id)
        # )
        CsvHelper.list2d_to_csv(
            dict_to_list2d(line_score), PATH + 'line_score_' + str(game_id)
        )
    except (KeyError, IndexError, TypeError, ValueError):
        print(summary)

    return True
    # with open('summary' + str(game_id), 'w') as fp:
    #     json.dump(game_summary, fp)


def regular(season):
    for x in range(1, 1230 + 1):
        num = '{:05}'.format(x)
        s = '{:02}'.format(season)
        # print(num)
        from hw_scrape.ParameterType import GameType

        find_detail(SEASON_HEAD + s + SEASON_SPLIT + '{:02}'.format(season + 1),
                    REGULAR_CODE + s + num,
                    GameType.traditional)
        find_summary(REGULAR_CODE + s + num)


def old_playoff(season):
    s = '{:02}'.format(season)
    for x in range(1, 105):
        rank = '{:03}'.format(x)
        res = find_detail(SEASON_HEAD + s + SEASON_SPLIT + '{:02}'.format(season + 1),
                          PLAY_OFF + s + '00' + rank,
                          GameType.traditional)
        if not res:
            break
        res = find_summary(PLAY_OFF + s + '00' + rank)
        if not res:
            break


# 0041400311
def playoff(season):
    for round_of in range(1, 4 + 1):
        r = '{:03}'.format(round_of)
        s = '{:02}'.format(season)
        for rank in range(0, 16 // (2 ** round_of)):
            for k in range(1, 7 + 1):
                from hw_scrape.ParameterType import GameType

                res = find_detail(SEASON_HEAD + s + SEASON_SPLIT + '{:02}'.format(season + 1),
                                  PLAY_OFF + s + r + str(rank) + str(k),
                                  GameType.traditional)
                if not res:
                    break
                    # res = find_summary(PLAY_OFF + s + r + str(rank) + str(k))
                    # if not res:
                    #     break


def pre_season(season):
    for x in range(1, 120):
        num = '{:05}'.format(x)
        # print(num)
        from hw_scrape.ParameterType import GameType

        find_detail(SEASON_HEAD + str(season) + SEASON_SPLIT + str(season + 1),
                    PRE_SEASON + str(season) + num,
                    GameType.traditional)
        # find_summary(PRE_SEASON + str(season) + num)


if __name__ == '__main__':
    # for sea in range(9, 10):
    #     regular(sea)
    #     playoff(sea)
    #     # pre_season(sea)

    season = 14
    find_detail(SEASON_HEAD + str(season) + SEASON_SPLIT + str(season + 1),
                PLAY_OFF + str(season) + '00405',
                GameType.traditional)

    find_summary('0041400405')

    # get regular game -- from 1 to 1230
    #

    # exceptions:
    # season=12, 0020000113
