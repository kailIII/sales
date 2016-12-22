#!/bin/bash

npm run build

docker build -t leosilvadev/sales-web .

