#!/usr/bin/python

import urllib2

def sendJson(url, data):
    clen = len(data)
    req = urllib2.Request(url, data, {'Content-Type': 'application/json', 'Content-Length': clen})
    return urllib2.urlopen(req)

def addData(test_parameters, data):
    f = sendJson('http://' + test_parameters['appserver_address'] + '/spray-test/addData', data)
    result = False
    if f.getcode() == 200:
        result = True
    f.close()
    return result

def readSavedDataCount(test_parameters):
    resp = urllib2.urlopen('http://' + test_parameters['appserver_address'] + '/spray-test/dataCount')
    if resp.getcode() != 200:
        resp.close()
        return -1
    dataRead = resp.read()
    resp.close()
    return int(dataRead)

def run(test_parameters):
    dataBefore = readSavedDataCount(test_parameters)

    addData(test_parameters, '{"bannedUserId":0,"banAuthorId":2,"created":"2015-08-18T13:37:31-08:00","lifted":"2016-03-16T13:36:32-08:00"}')
    addData(test_parameters, '{"bannedUserId":1,"banAuthorId":10,"created":"2013-11-16T13:36:32-08:00","lifted":"2016-03-16T13:36:32-08:00"}')
    addData(test_parameters, '{"bannedUserId":4,"banAuthorId":5,"created":"2012-03-17T14:36:32-08:00","lifted":"2016-03-16T13:36:32-08:00"}')

    dataAfter = readSavedDataCount(test_parameters)

    return (dataBefore + 3) == dataAfter
