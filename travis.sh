#!/bin/bash

./fetchIdea.sh


./gradlew test

# Was our build successful?
stat=$?

# Return the build status
exit ${stat}
