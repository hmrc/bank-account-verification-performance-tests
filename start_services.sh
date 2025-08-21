#!/usr/bin/env bash

sm2 --start BANK_ACCOUNT_REPUTATION_STUB && sm2 --start BANK_ACCOUNT_VERIFICATION --appendArgs '{
  "BANK_ACCOUNT_VERIFICATION_FRONTEND": [
    "-J-Dmicroservice.services.access-control.enabled=false"
  ]
}
'