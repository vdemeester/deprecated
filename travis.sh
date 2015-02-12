#!/bin/bash

# Compile Java and tests
./gradlew test

# Was our build successful?
stat=$?

# Return the build status
exit ${stat}
