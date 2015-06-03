import sys

PORTAL_PORTAL_IO_DO = 'http://p.nju.edu.cn/portal/portal_io.do'
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
        p = s.post(PORTAL_PORTAL_IO_DO, data=payload)
        # print the html returned or something more intelligent to see if it's a successful login page.
        # print(p.text)

        # An authorised request.
        r = s.get('http://www.baidu.com')
        print(r.text)


def log_out():
    payload = {
        'action': 'logout'
    }

    with requests.Session() as s:
        p = s.post(PORTAL_PORTAL_IO_DO, data=payload)
        print(p.text)


if __name__ == '__main__':
    if len(sys.argv) == 1:
        log_in()
    else:
        log_out()
