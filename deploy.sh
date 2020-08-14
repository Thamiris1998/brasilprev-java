#!/usr/bin/env bash
set -x
./gradlew '-Dfile.encoding=UTF-8' clean build
cp app.yaml build/libs
cp Dockerfile build/libs
mv build/libs/Application-0.0.1-SNAPSHOT-all.jar build/libs/spring-boot.jar
gcloud config set project thamiris-lopes --quiet
gcloud app deploy build/libs/app.yaml --quiet