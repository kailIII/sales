#!/bin/bash

./gradlew installDist

docker build -t leosilvadev/sales-api .

