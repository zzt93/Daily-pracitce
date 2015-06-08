import traceback
import requests
from hw_scrape import CsvHelper
from hw_scrape.AllPlayers import find_all_players
from hw_scrape.CsvHelper import dict_to_list2d
from hw_scrape.ParameterType import SeasonType

__author__ = 'zzt'


def valid_response(response):
    if response.status_code >= 400:
        print('no suck url ' + str(response.status_code) + ' ' + response.url)
        # print(traceback.format_exc())
        return False
    return True


def player_profile(player_id, season=SeasonType.regular):
    url = 'http://stats.nba.com/stats/commonplayerinfo?LeagueID=00&PlayerID=' \
          + str(player_id) + \
          '&SeasonType=' \
          + season.value

    response = requests.get(url)
    if not valid_response(response):
        return

    stats = response.json()['resultSets'][0]
    CsvHelper.list2d_to_csv(
        dict_to_list2d(stats), 'player_profile_' + str(player_id)
    )


def find_every_player():
    stats = find_all_players(1)
    l2d = dict_to_list2d(stats)[1:]
    for l in l2d:
        player_profile(l[0])


if __name__ == '__main__':
    find_every_player()
    # player_profile(203112)
