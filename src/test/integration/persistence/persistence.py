#!/usr/bin/python

from testapi import api


def testCats(test_parameters):
    catsBefore = api.readSavedCatsNumber(test_parameters)
    api.addCat(test_parameters, '{"name":"Jack","sex":"MALE","groupId":2,"birthDate":"2015-08-18T13:38:21-08:00"}')
    api.addCat(test_parameters, '{"name":"Frank","sex":"MALE","groupId":1,"birthDate":"2015-08-18T13:37:31-08:00"}')
    api.addCat(test_parameters, '{"name":"Sussie","sex":"FEMALE","groupId":2,"birthDate":"2015-08-18T21:37:00-08:00"}')
    catsAfter = api.readSavedCatsNumber(test_parameters)
    return (catsBefore + 3) == catsAfter

def testGroups(test_parameters):
    groupsBefore = api.readSavedGroupsNumber(test_parameters)
    api.addGroup(test_parameters, '{"name":"Pirates"}')
    api.addGroup(test_parameters, '{"name":"Corsairs"}')
    groupsAfter = api.readSavedGroupsNumber(test_parameters)
    return (groupsBefore + 2) == groupsAfter

def run(test_parameters):
    return testCats(test_parameters) and testGroups(test_parameters)
