# -*- coding: utf-8 -*-
__author__ = 'zzt'
# !/usr/bin/python -tt
# from https://pythonhosted.org/kitchen/unicode-frustrations.html

def t1():
    string = unicode(raw_input(), 'utf-8')
    log = open('debug.log', 'w')
    log.write(string)


def t2():
    string = unicode(raw_input(), 'utf-8')
    log = open('debug.log', 'w')
    strout = string.encode('utf-8')
    log.write(strout)


def t3():
    import codecs
    import sys

    UTF8Writer = codecs.getwriter('utf8')
    sys.stdout = UTF8Writer(sys.stdout)
    print u'café'


def t4():
    import codecs
    import sys

    UTF8Writer = codecs.getwriter('utf8')
    sys.stdout = UTF8Writer(sys.stdout)
    print 'café'


if __name__ == '__main__':
    # t1()
    # t2()
    # t3()
    t4()
