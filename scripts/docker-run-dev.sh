#!/usr/bin/env bash

CUR_DIR=$(pwd);
echo ${CUR_DIR};

if [[ "$CUR_DIR" =~ ^.*/web$ ]]; then
    echo "In correct directory"
else
    echo "Please cd to project root"
    exit 1;
fi


BUILD_SOURCE="${CUR_DIR}/scripts/docker-build.sh";

source ${BUILD_SOURCE} && docker run -p 8080:8080 co/hadwen/web;
unset CUR_DIR;
unset BUILD_SOURCE;
