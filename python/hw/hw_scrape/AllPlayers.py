import requests
from hw_scrape.Games import SEASON_HEAD, SEASON_SPLIT

__author__ = 'zzt'


def find_all_players(current, season='2014-15'):
    # http://stats.nba.com/stats/commonallplayers?IsOnlyCurrentSeason=
    # 1&LeagueID=00&Season=20
    # 14-15
    all = 'http://stats.nba.com/stats/commonallplayers?IsOnlyCurrentSeason=' \
          + str(current) + \
          '&LeagueID=00&Season=' \
          + season

    players_resp = requests.get(all)
    if players_resp.status_code >= 400:
        print('wrong url for players')
        return

    players = players_resp.json()['resultSets'][0]

    from hw_scrape import CsvHelper
    from hw_scrape.CsvHelper import dict_to_list2d

    CsvHelper.list2d_to_csv(
        dict_to_list2d(players), 'players_' + str(current) + '_' + season
    )
    return players


if __name__ == '__main__':
    curr = 1
    s = 14
    sea = SEASON_HEAD + str(s) + SEASON_SPLIT + str(s + 1)

    find_all_players(curr, sea)
