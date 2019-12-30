#!/usr/bin/env bash
mvn package && docker build --build-arg JAR_FILE=target/HadwenWeb*.jar -t co/hadwen/web:hadwen-web-v1 .
