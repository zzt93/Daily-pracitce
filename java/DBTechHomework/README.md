
##How to run

###IDE:
- Creating project from existing source
- Choose maven project
- Run maven build

###command line
- maven test



##Requirement:
- java 8
- mongodb server version should not smaller than 2.6


##Opt:
- Are the tables structured properly?
 In particular, do the columns have the right data types,
 and does each table have the appropriate columns for the type of work?
 For example, applications that perform frequent updates often
 have many tables with few columns, while applications that
 analyze large amounts of data often have few tables with many columns.


##Question:
1.比较mysql和mongodb,说说他们各自的特点
- the syntax of mysql is like english which is somewhat strange; mongo db is better for
it is much more like a computer language
- the driver of mongo db is much more like a java library, while mysql is still executing string
- I feel mongo db is easier to use for it somewhat loose syntax, but also hide some errors
- mongo db is more suitable for OO development for it can easily handle complicated objects


2.对原来的mysql数据库进行扩充,以满足刘嘉
  老师的需求(本小题需在文档中写明思路)

  - add new table `Analysis`(tid integer NOT NULL, cid integer NOT NULL, avg integer NOT NULL, region varchar(40) NOT NULL, grade integer NOT NULL)
  - insert rows group by semester
  - add new semester data by date
