#!/bin/bash

cd api

echo "Starting web api..."
./gradlew clean build flywayMigrate -i installDist
nohup ./build/install/api/bin/api > /dev/null 2>&1 &

echo "Starting web app..."
cd ../web
npm run server-background

