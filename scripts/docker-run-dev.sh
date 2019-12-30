#!/usr/bin/env bash
./docker-build.sh && docker run -p 8080:8080 co/hadwen/web
