#!/usr/bin/env bash
docker push hadwen/web:v1
kubectl create deployment hadwen-web --image=hadwen/web:v1

