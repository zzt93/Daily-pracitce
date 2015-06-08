import requests
from hw_scrape.ParameterType import SeasonType, GameType, TeamType
from hw_scrape.Games import SEASON_HEAD, SEASON_SPLIT

UNDER = '_'

__author__ = 'zzt'


def find_all_teams(data_type=TeamType.Base,
                   season='2014-15',
                   season_type=SeasonType.regular):
    # http://stats.nba.com/stats/leaguedashteamstats?Conference=&DateFrom=&DateTo=&Division=&GameScope=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=
    # Base
    # &Month=0&OpponentTeamID=0&Outcome=&PORound=0&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerExperience=&PlayerPosition=&PlusMinus=N&Rank=N&Season=
    # 2014-15
    # &SeasonSegment=&SeasonType=
    # Regular+Season
    # &ShotClockRange=&StarterBench=&TeamID=0&VsConference=&VsDivision=
    url = 'http://stats.nba.com/stats/leaguedashteamstats?Conference=&DateFrom=&DateTo=&Division=&GameScope=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=' \
          + data_type.name + \
          '&Month=0&OpponentTeamID=0&Outcome=&PORound=0&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerExperience=&PlayerPosition=&PlusMinus=N&Rank=N&Season=' \
          + season + \
          '&SeasonSegment=&SeasonType=' \
          + season_type.value + \
          '&ShotClockRange=&StarterBench=&TeamID=0&VsConference=&VsDivision='
    # http://stats.nba.com/stats/leaguedashteamstats?Conference=&DateFrom=&DateTo=&Division=&GameScope=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=
    # Base
    # &Month=0&OpponentTeamID=0&Outcome=&PORound=0&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerExperience=&PlayerPosition=&PlusMinus=N&Rank=N&Season=
    # 2014-15
    # &SeasonSegment=&SeasonType=
    # Playoffs
    # &ShotClockRange=&StarterBench=&TeamID=0&VsConference=&VsDivision=
    resp = requests.get(url)
    if resp.status_code >= 400:
        print('wrong url for teams')
        return

    teams = resp.json()['resultSets'][0]

    # from hw_scrape import CsvHelper
    # from hw_scrape.CsvHelper import dict_to_list2d
    #
    # CsvHelper.list2d_to_csv(
    #     dict_to_list2d(teams),
    #     'teams_' + str(data_type.name) + UNDER + season + UNDER + season_type.value
    # )
    return teams


if __name__ == '__main__':
    s = 14
    sea = SEASON_HEAD + str(s) + SEASON_SPLIT + str(s + 1)

    find_all_teams()
