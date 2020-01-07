#!/usr/bin/env bash
docker push hadwen/web
kubectl create deployment hadwen-web --image=hadwen/web

