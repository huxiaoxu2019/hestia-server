#!/bin/bash

pid=`ps -ef | grep "java com.ihuxu.hestia.server.controller.BootstrapController" | grep -v "grep" | awk '{print $2}'`
if [ $pid -gt 0 ];then
    `kill -9 ${pid}`
    echo "hestia server(pid ${pid}) has been stopped."
else
    echo "no hestia server running."
fi
