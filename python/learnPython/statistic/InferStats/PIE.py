import requests
from hw_scrape.AllPlayers import find_all_players
from hw_scrape.CsvHelper import dict_to_list2d
from hw_scrape.ParameterType import PIEType
from hw_scrape.PlayerBasic import valid_response

FORBID = 1

BENCH = 3

STARTER = 2

PIE_INDEX = 21

__author__ = 'zzt'


def get_pie(player_id, season='2014-15', ptype=PIEType.rest):
    url = 'http://stats.nba.com/stats/playerdashboardbygeneralsplits?DateFrom=&DateTo=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=Advanced&Month=0&OpponentTeamID=0&Outcome=&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerID=' \
          + str(player_id) + \
          '&PlusMinus=N&Rank=N&Season=' \
          + season + \
          '&SeasonSegment=&SeasonType=Regular+Season&VsConference=&VsDivision='

    resp = requests.get(url)
    if not valid_response(resp):
        return

    # check whether this player meet the requests
    result_sets = resp.json()['resultSets']

    if ptype == PIEType.overall:
        keep = FORBID
        for row in result_sets[5]['rowSet']:
            if row[1] == 'Starters' and row[2] > 42:
                keep = STARTER
                break
            elif row[1] == 'Bench' and row[2] > 42:
                keep = BENCH
                break

        pie = [keep]
        try:
            overall_pie = result_sets[0]['rowSet'][0][PIE_INDEX]
            pie.append(overall_pie)
            return pie
        except (KeyError, IndexError, TypeError):
            return pie

    # diminish some game < 3
    rest_pie = [player_id]
    j = 0
    for i in range(0, 7):
        if j >= len(result_sets[6]['rowSet']):
            rest_pie.append(0)
            continue
        row = result_sets[6]['rowSet'][j]
        if row[2] < 3:
            j += 1
            rest_pie.append(0)
        elif row[1][0] != str(i):
            rest_pie.append(0)
        else:
            j += 1
            rest_pie.append(row[PIE_INDEX])

    return rest_pie


def get_overall_pie(season):
    stats = find_all_players()
    l2d = dict_to_list2d(stats)[1:]
    starter = []
    bench = []
    for l in l2d:
        pie = get_pie(l[0], season, ptype=PIEType.overall)
        if pie[0] == STARTER:
            starter.append(pie[1])
        elif pie[0] == BENCH:
            bench.append(pie[1])

    # list2d_to_csv(starter, 'starter_pie')
    # list2d_to_csv(bench, 'bench_pie')
    return [starter, bench]


if __name__ == '__main__':
    get_overall_pie('2014-15')
