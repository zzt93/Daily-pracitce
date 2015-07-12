import os
import requests

IQIYI = 'http://www.iqiyi.com/common/flashplayer/20150710/MainPlayer_5_2_25_c3_3_5_1.swf'
__author__ = 'zzt'


def media():
    site = IQIYI
    response = requests.get(site)
    response.encoding = 'utf-8'
    outpath = os.path.join(os.getcwd(), 'video.mp4')
    with open(outpath, 'w', encoding='utf-8') as videofile:
        videofile.write(response.text)
        videofile.close()


if __name__ == '__main__':
    media()
