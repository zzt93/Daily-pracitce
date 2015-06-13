# -*- coding:utf-8 -*-

import requests
from sys import argv

EVAL_COURSE = 'http://jwas2.nju.edu.cn:8080/jiaowu/student/evalcourse/courseEval.do'

COURSE_EVAL_SUBMIT = 'http://jwas2.nju.edu.cn:8080/jiaowu/student/evalcourse/courseEval.do?method=submitEval'

EVAL_COURSE_LIST = 'http://jwas2.nju.edu.cn:8080/jiaowu/student/evalcourse/courseEval.do?method=currentEvalCourse'

JW_LOG = 'http://jwas2.nju.edu.cn:8080/jiaowu/login.do'

__author__ = 'zzt'


def jw(user, pw):
    # Fill in your details here to be posted to the login form.
    payload = {
        'userName': user,
        'password': pw
    }

    # Use 'with' to ensure the session context is closed after use.
    with requests.Session() as s:
        # login
        tmp = s.post(JW_LOG, data=payload)
        # print the html returned or something more intelligent to see if it's a successful login page.
        # print(p.text)
        course_list_resp = s.get(EVAL_COURSE_LIST)
        if (tmp.status_code >= 400) \
                or course_list_resp.status_code >= 400:
            print('Invalid user name or password')
            return

        # print(course_list_resp.text)
        from bs4 import BeautifulSoup

        soup = BeautifulSoup(course_list_resp.text)
        course_list = []
        for tag in soup.find_all('tr'):
            ids = tag.get('id')
            if ids is not None:
                course_list.append(ids[2:])

        evl = {
            'question1': '4',
            'question2': '4',
            'question3': '4',
            'question4': '4',
            'question5': '4',
            'question6': '4',
            'question7': '4',
            'question8': '4',
            'question9': '4',
            'question10': '4',
            'question': '+10',
            # overall
            'mulItem1': '2',
            'mulItem': '+1',
            # what you want to say
            'ta1': '',
            'sub': '提+++交',
        }
        for cid in course_list:
            course = {
                'method': 'currentEvalItem',
                'id': cid
            }
            tmp = s.post(EVAL_COURSE, data=course)
            print(tmp)
            res = s.post(COURSE_EVAL_SUBMIT, data=evl)
            print(res)


if __name__ == '__main__':
    name = input('Enter user name: ')
    password = input('Enter password: ')
    jw(name, password)
