#!/usr/bin/env bash

CURDIR=$(cd "$(dirname "$0")"; pwd -P)

source "$CURDIR/.env"

docker attach $SCALA_HOST