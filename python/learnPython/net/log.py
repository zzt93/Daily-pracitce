import sys

PORTAL_IN = 'http://p.nju.edu.cn/portal_io/login'
PORTAL_OUT = 'http://p.nju.edu.cn/portal_io/logout'

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
        r = s.get('https://www.baidu.com')
        print(r.status_code)


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
