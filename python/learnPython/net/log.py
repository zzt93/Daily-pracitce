__author__ = 'zzt'

import requests

# Fill in your details here to be posted to the login form.
payload = {
    'inUserName': '131250072',
    'inUserPass': '142333199303151816'
}

login_url = 'http://p.nju.edu.cn/portal/index.html?t=1432949960'

# Use 'with' to ensure the session context is closed after use.
with requests.Session() as s:
    p = s.post(login_url, data=payload)
    # print the html returned or something more intelligent to see if it's a successful login page.
    print p.text

    # An authorised request.
    r = s.get('A protected web page url')
    print r.text
