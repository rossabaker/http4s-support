#!/usr/bin/env bash

eval "$(docker-machine env $1)"

CURDIR=$(cd "$(dirname "$0")"; pwd -P)

trap ctrl_c INT

SBT_RUN="sbt -jvm-debug 5005 run"

function ctrl_c() {
    docker exec $SCALA_HOST /bin/bash -c 'kill -TERM -$(ps aux | grep '"'$SBT_RUN'"" | awk '"'{print $2}'"')"
}

source "$CURDIR/.env"
docker exec $SCALA_HOST $SBT_RUN