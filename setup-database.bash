#!/bin/bash
# Attention, this script is used in the CI/CD process in .github/workflows/build-master.yaml

docker run -d --name karuna-project-be-db \
  -e POSTGRES_DB=database \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16
