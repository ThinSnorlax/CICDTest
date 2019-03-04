#!/bin/bash
# 删除旧的包
if rm /usr/local/tomcat/apache-tomcat-9.0.16/webapps/snor-test.war ;then
    echo "Successfully" 1>&2
else
    echo "Failed" 1>&2
fi
