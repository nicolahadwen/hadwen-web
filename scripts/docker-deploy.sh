#!/usr/bin/env bash
./scripts/docker-build.sh
docker push hadwen/web
kubectl apply -f ./deployment.yaml
