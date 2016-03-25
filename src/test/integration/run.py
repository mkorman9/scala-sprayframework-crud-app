#!/usr/bin/python

import sys
import smoke.service_status


TEST_PARAMETERS = {
    'appserver_address': 'localhost:8080'
}

TESTS = {
    'Service status': smoke.service_status
}

for t in TESTS:
    if not TESTS[t].run(TEST_PARAMETERS):
        print('Test %s failed!' % t)
        sys.exit(1)

print('All tests passed')
sys.exit(0)
