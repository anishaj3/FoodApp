#!/bin/bashp

cd userservice
source ./env-variables.sh
mvn clean package
cd ..
cd favouriteservice
source ./env-variables.sh
mvn clean package
cd ..