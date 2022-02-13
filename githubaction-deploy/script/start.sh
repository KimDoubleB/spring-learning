#!/bin/bash

# parameters
containerImage=$1
containerTag=$2

# if latset tag, finish
if [ $containerTag = "latest" ]
then
    exit 0
fi

envFileName=`echo "$containerTag" | awk -F- '{ print $1 }'`
containerId=`docker ps | grep "$containerImage" | awk '{ print $1 }'`
env="/home/ec2-user/github-action/$envFileName.env"

# stop container
if [ ! -z "$containerId" ]
then
    docker stop $containerId
fi

# start container
docker run -d -p 8080:8080 --env-file "$env" "$containerImage":"$containerTag"