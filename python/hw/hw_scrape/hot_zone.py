import requests
from hw_scrape.AllPlayers import find_all_players
from hw_scrape.AllTeams import UNDER
from hw_scrape.CsvHelper import dict_to_csv, dict_to_list2d
from hw_scrape.PlayerBasic import valid_response

__author__ = 'zzt'


def find_hot(player_name):
    url = 'http://china.nba.com/wap/static/data/player/hotzone_' \
          + player_name + \
          '.json'

    response = requests.get(url)
    response.encoding = 'utf-8'
    if not valid_response(response):
        return

    try:
        pid = response.json()['payload']['profile']['playerId']
        regu_last_5 = response.json()['payload']['hotZone']['seasons'][1]['splits'][0]['full']
        regu_total = response.json()['payload']['hotZone']['seasons'][1]['splits'][1]['full']

        regu_last_5['playerId'] = pid
        regu_total['playerId'] = pid

        dict_to_csv(
            regu_last_5, 'regular_last5_' + player_name
        )
        dict_to_csv(
            regu_total, 'regular_total_' + player_name
        )

        bef_last_5 = response.json()['payload']['hotZone']['seasons'][0]['splits'][0]['full']
        bef_total = response.json()['payload']['hotZone']['seasons'][0]['splits'][1]['full']

        bef_last_5['playerId'] = pid
        bef_total['playerId'] = pid

        dict_to_csv(
            bef_last_5, 'pre_last5_' + player_name
        )
        dict_to_csv(
            bef_total, 'pre_total_' + player_name
        )
    except (KeyError, IndexError, TypeError):
        print('no such player ' + player_name)


def find_every_player_hot():
    stats = find_all_players(1)
    l2d = dict_to_list2d(stats)[1:]
    i = 0
    for l in l2d:
        print(i)
        names = l[1].lower().split(',')
        # assert len(names) == 2
        find_hot(names[1].strip() + UNDER + names[0].strip())
        i += 1


if __name__ == '__main__':
    # exceptions:
    # find_hot('jose_barea')
    # find_hot('nene')
    find_every_player_hot()
