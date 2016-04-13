#!/usr/bin/python

import sys
import smoke.service_status
import persistence.persistence

# Integration tests
# Executed by TravisCI after each push

TEST_PARAMETERS = {
    'appserver_address': 'localhost:8080',
    'context': 'spray-test'
}

TESTS = {
    'Service status': smoke.service_status,
    'Data persistence': persistence.persistence
}

for t in TESTS:
    if not TESTS[t].run(TEST_PARAMETERS):
        print('Test %s failed!' % t)
        sys.exit(1)

print('All tests passed')
sys.exit(0)
