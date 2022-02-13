#!/bin/bash

# parameters
containerImage=$1
containerTag=$2

# if latset tag, finish
if [ $containerTag = "latest" ]
then
    exit 0
fi

profile=`echo "$containerTag" | awk -F- '{ print $1 }'`
containerId=`docker ps | grep "$containerImage:$profile" | awk '{ print $1 }'`
env="/home/ec2-user/github-action/$profile.env"

# set port
port=8080
if [ $profile = "local" ]
then
    port=8081
fi

# stop container
if [ ! -z "$containerId" ]
then
    docker stop $containerId
fi

# start container
docker run -d -p $port:$port --env-file $env $containerImage:$containerTag