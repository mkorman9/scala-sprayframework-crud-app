#!/usr/bin/python

import urllib2


def run(test_parameters):
    resp = urllib2.urlopen('http://' + test_parameters['appserver_address'] + '/spray-test/allData')

    return resp.getcode() == 200
