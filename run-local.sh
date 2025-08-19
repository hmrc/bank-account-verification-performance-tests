#!/usr/bin/env bash

SMOKE_TEST=${1:-true}

sbt -Dperftest.runSmokeTest=${SMOKE_TEST} -DrunLocal=true gatling:test \
 -DjourneysToRun.0=bank-account-verification-frontend-personal-stubbed \
 -DjourneysToRun.1=bank-account-verification-frontend-business-stubbed
