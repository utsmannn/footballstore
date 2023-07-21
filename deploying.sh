#!/bin/bash

./gradlew clean assemble -Dquarkus.container-image.build=true && flyctl deploy --dockerfile src/main/docker/Dockerfile.jvm