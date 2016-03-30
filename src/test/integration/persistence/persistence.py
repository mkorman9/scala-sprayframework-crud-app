#!/usr/bin/python

import urllib2

def sendJson(url, data):
    clen = len(data)
    req = urllib2.Request(url, data, {'Content-Type': 'application/json', 'Content-Length': clen})
    return urllib2.urlopen(req)

def addData(test_parameters, entity, data):
    f = sendJson('http://%s/spray-test/%s/add' % (test_parameters['appserver_address'], entity), data)
    result = False
    if f.getcode() == 200:
        result = True
    f.close()
    return result

def addCat(test_parameters, data):
    return addData(test_parameters, 'cats', data)

def addGroup(test_parameters, data):
    return addData(test_parameters, 'groups', data)

def readSavedDataCount(test_parameters, entity):
    resp = urllib2.urlopen('http://%s/spray-test/%s/count' % (test_parameters['appserver_address'], entity))
    if resp.getcode() != 200:
        resp.close()
        return -1
    dataRead = resp.read()
    resp.close()
    return int(dataRead)

def getSavedCatsNumber(test_parameters):
    return readSavedDataCount(test_parameters, 'cats')

def getSavedGroupsNumber(test_parameters):
    return readSavedDataCount(test_parameters, 'groups')


def testCats(test_parameters):
    catsBefore = getSavedCatsNumber(test_parameters)
    addCat(test_parameters, '{"name":"Jack","groupId":2,"birthDate":"2015-08-18T13:38:21-08:00"}')
    addCat(test_parameters, '{"name":"Frank","groupId":1,"birthDate":"2015-08-18T13:37:31-08:00"}')
    addCat(test_parameters, '{"name":"Sussie","groupId":2,"birthDate":"2015-08-18T21:37:00-08:00"}')
    catsAfter = getSavedCatsNumber(test_parameters)
    return (catsBefore + 3) == catsAfter

def testGroups(test_parameters):
    groupsBefore = getSavedGroupsNumber(test_parameters)
    addCat(test_parameters, '{"name":"Pirates"}')
    addCat(test_parameters, '{"name":"Corsairs"}')
    groupsAfter = getSavedGroupsNumber(test_parameters)
    return (groupsBefore + 2) == groupsAfter

def run(test_parameters):
    return testCats(test_parameters) and testGroups(test_parameters)
