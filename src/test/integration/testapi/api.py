#!/usr/bin/python

import urllib2


def isAvailable(test_parameters):
    resp = urllib2.urlopen('http://%s/%s/cats/all' % (test_parameters['appserver_address'], test_parameters['context']))
    return resp.getcode() == 200
     
def sendJson(url, data):
    clen = len(data)
    req = urllib2.Request(url, data, {'Content-Type': 'application/json', 'Content-Length': clen})
    return urllib2.urlopen(req)

def addData(test_parameters, entity, data):
    f = sendJson('http://%s/%s/%s/add' % (test_parameters['appserver_address'], test_parameters['context'], entity), data)
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
    resp = urllib2.urlopen('http://%s/%s/%s/count' % (test_parameters['appserver_address'], test_parameters['context'], entity))
    if resp.getcode() != 200:
        resp.close()
        return -1
    dataRead = resp.read()
    resp.close()
    return int(dataRead)

def readSavedCatsNumber(test_parameters):
    return readSavedDataCount(test_parameters, 'cats')

def readSavedGroupsNumber(test_parameters):
    return readSavedDataCount(test_parameters, 'groups')
