__author__ = 'zzt'

import sys
from cx_Freeze import setup, Executable

setup(
    name="jw teacher evaluation",
    version="0.1",
    description="A tool to fill questionnaire",
    executables=[Executable("jw.py")])
