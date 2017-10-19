#!/bin/bash

echo "compiling..."
cd src
javac -d ../bin com/ihuxu/hestia/server/controller/BootstrapController.java -Xlint:deprecation

echo "building..."
cd ../bin
mkdir ../run
chmod -R 777 ../run
nohup java com.ihuxu.hestia.server.controller.BootstrapController > ../run/server.log &   
