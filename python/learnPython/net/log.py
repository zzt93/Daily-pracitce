import sys

PORTAL_IN = 'http://p.nju.edu.cn/portal_io/login'
PORTAL_OUT = 'http://p.nju.edu.cn/portal_io/logout'
PORTAL_USER = 'http://p.nju.edu.cn/portal_io/proxy/userinfo'

__author__ = 'zzt'

import requests


def log_in():
    # Fill in your details here to be posted to the login form.
    payload = {
        'action': 'login',
        'username': '',
        'password': ''
    }

    # Use 'with' to ensure the session context is closed after use.
    with requests.Session() as s:
        p = s.post(PORTAL_IN, data=payload)
        # print the html returned or something more intelligent to see if it's a successful login page.
        # print(p.text)

        # An authorised request.
        try:
            r = s.post(PORTAL_USER).json()['results']['total_time']
        except KeyError:
            # r = s.post(PORTAL_USER).json()['results']['total_time']
        # r = s.get('https://www.baidu.com')
        print('{}h{}m{}s'.format(r // 3600, r % 3600 // 60, r % 3600 % 60))


def log_out():
    # payload can be more, but no less
    # payload = {
    #    'action': 'logout'
    # }

    with requests.Session() as s:
        p = s.post(PORTAL_OUT)
        print(p.status_code)


if __name__ == '__main__':
    if len(sys.argv) == 1:
        log_in()
    else:
        log_out()
