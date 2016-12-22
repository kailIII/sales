#!/bin/bash

export DB_URL="jdbc:postgresql://localhost:5432/sales"
export DB_USERNAME=dev
export DB_PASSWORD=dev

cd api

echo "Starting web api..."
./gradlew clean build flywayMigrate -i installDist
nohup ./build/install/api/bin/api > /dev/null 2>&1 &

echo "Starting web app..."
cd ../web
npm run server-background

