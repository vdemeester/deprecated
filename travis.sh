#!/bin/bash

# Compile Java and tests
./gradlew compileJava compileTestJava

# Was our build successful?
stat=$?

# Return the build status
exit ${stat}
