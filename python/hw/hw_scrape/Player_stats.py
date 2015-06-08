import requests
from hw_scrape import ParameterType

from hw_scrape.AllPlayers import find_all_players
from hw_scrape.CsvHelper import list2d_to_csv, dict_to_list2d
from hw_scrape.ParameterType import PlayerType

__author__ = 'zzt'


def find_player(player_id, player_data_type, season_type=ParameterType.SeasonType.regular):
    url = 'http://stats.nba.com/stats/playerdashboardbygeneralsplits?DateFrom=&DateTo=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=' \
          + player_data_type.name + \
          '&Month=0&OpponentTeamID=0&Outcome=&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerID=' \
          + str(player_id) + \
          '&PlusMinus=N&Rank=N&Season=2014-15&SeasonSegment=&SeasonType=' \
          + season_type.value + \
          '&VsConference=&VsDivision='
    response = requests.get(url)
    if response.status_code > 400:
        print('no such player')
        return

    overall = response.json()['resultSets'][0]
    list2d_to_csv(
        dict_to_list2d(overall), 'player_' + str(player_id) + '_' + player_data_type.name
    )


def find_every_player():
    stats = find_all_players(1)
    l2d = dict_to_list2d(stats)[1:]
    for l in l2d:
        find_player(l[0], PlayerType.Base)


if __name__ == '__main__':
    find_every_player()
