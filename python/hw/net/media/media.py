import os
import requests

IQIYI = 'http://static.youku.com/v1.0.0555/v/swf/loader.swf'
__author__ = 'zzt'


def media():
    site = IQIYI
    para = {
        'allowFullScreen': 'true',
        'allowscriptaccess': 'true',
        'allowFullScreenInteractive': 'true',
        'flashvars': 'VideoIDS=XMTI5ODEzNTM2MA==&ShowId=274380&category=100&Cp=authorized&Light=on&THX=off&unCookie=0&frame=0&pvid=1438420817163HZSbtb&uepflag=1&Tid=0&isAutoPlay=true&Version=/v1.0.1078&show_ce=0&winType=interior&openScanCode=1&scanCodeText="限时" 扫码免广告&embedid=AjMyNDUzMzg0MAJ3d3cueW91a3UuY29tAi9zaG93X3BhZ2UvaWRfejlhOGU5YmEwNjA1NjExZTJhMTllLmh0bWw=&vext=bc%3D%26pid%3D1438420817163HZSbtb%26unCookie%3D0%26frame%3D0%26type%3D0%26svt%3D1%26stg%3D10%26emb%3DAjMyNDUzMzg0MAJ3d3cueW91a3UuY29tAi9zaG93X3BhZ2UvaWRfejlhOGU5YmEwNjA1NjExZTJhMTllLmh0bWw%3D%26dn%3D%E7%BD%91%E9%A1%B5%26hwc%3D1%26mtype%3Doth&pageStartTime=1438420817162',
        'movie': 'http://static.youku.com/v1.0.0555/v/swf/loader.swf'
    }
    response = requests.post(site, para)
    response.encoding = 'gbk'
    outpath = os.path.join(os.getcwd(), 'video.mp4')
    with open(outpath, 'wb') as videofile:
        videofile.write(response.text.encode('gbk'))
        videofile.close()


if __name__ == '__main__':
    media()
