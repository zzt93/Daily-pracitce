from practise.Scrape import CsvHelper
from practise.Scrape.DetailType import DetailType
import requests

PLAY_OFF = '004'

REGULAR_CODE = '002'

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
    if detail_resp.status_code > 400:
        print('no suck game ' + str(detail_resp.status_code))
        return

    player_stats = detail_resp.json()['resultSets'][0]
    team_stats = detail_resp.json()['resultSets'][1]

    CsvHelper.list2d_to_csv(
        dict_to_list2d(player_stats), 'player_' + str(game_id) + str(detail_type.name)
    )
    CsvHelper.list2d_to_csv(
        dict_to_list2d(team_stats), 'team_' + str(game_id) + str(detail_type.name)
    )

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
    if summary_resp.status_code > 400:
        print('no suck game ' + str(summary_resp.status_code))
        return

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


def regular(season):
    for x in range(1, 1230 + 1):
        num = '{:05}'.format(x)
        # print(num)
        find_detail(SEASON_HEAD + str(season) + SEASON_SPLIT + str(season + 1),
                    REGULAR_CODE + str(season) + num,
                    DetailType.traditional)
        find_summary(REGULAR_CODE + str(season) + num)


# 0041400311
def playoff(season):
    for round_of in range(1, 4 + 1):
        r = '{:03}'.format(round_of)
        for rank in range(0, 16 / (2 ** round_of)):
            for k in range(1, 7 + 1):
                find_detail(SEASON_HEAD + str(season) + SEASON_SPLIT + str(season + 1),
                            PLAY_OFF + str(season) + r + str(rank) + str(k),
                            DetailType.traditional)
                find_summary(PLAY_OFF + str(season) + r + str(rank) + str(k))


if __name__ == '__main__':

    for sea in range(12, 15):
        regular(sea)
        playoff(sea)

    # find_detail(SEASON_HEAD + str(season) + SEASON_SPLIT + str(season + 1),
    #             regular_date_code + str(season) + '00001',
    #             DetailType.traditional)
    #
    # find_summary(regular_date_code + str(season) + '00001')

    # get regular game -- from 1 to 1230
    #
