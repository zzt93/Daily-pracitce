import requests
from hw_scrape.AllTeams import find_all_teams
from hw_scrape.CsvHelper import dict_to_csv, dict_to_list2d, list2d_to_csv
from hw_scrape.Games import UNDER
from hw_scrape.PlayerBasic import valid_response

__author__ = 'zzt'

PATH = '/home/zzt/hw2l/trunk/NBADataAnalysisSystem/IterationThreeData/teams/basic/'

def team_profile(team_id, season='2014-15'):
    url = 'http://stats.nba.com/stats/teaminfocommon?LeagueID=00&SeasonType=Regular+Season&TeamID=' \
          + str(team_id) + \
          '&season=' \
          + str(season)

    response = requests.get(url)
    if not valid_response(response):
        return

    stats = response.json()['resultSets'][0]
    list2d_to_csv(
        dict_to_list2d(stats), PATH + 'team_profile_' + str(team_id) + UNDER + season
    )


def find_every_team(season='2013-14'):
    stats = find_all_teams(season=season)
    l2d = dict_to_list2d(stats)[1:]
    for l in l2d:
        team_profile(l[0], season)


if __name__ == '__main__':
    find_every_team()
