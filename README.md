
# Bank Account Verification performance tests

Performance test repository fo the bank-account-verification services

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
   
## Running tests   
    
### Smoke test

To run one journey with one user
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

### Run the performance test (Default)
```
sbt gatling:test
```
## Running locally

First of all you will need to spin up an environment that can be tested locally using the following:

```
sm --start BANK_ACCOUNT_REPUTATION_STUB && sm --start BANK_ACCOUNT_VERIFICATION -r --appendArgs '{
  "BANK_ACCOUNT_REPUTATION": [
    "-J-Dmicroservice.services.callvalidate.endpoint=http://localhost:9000/callvalidateapi",
    "-J-Dmicroservice.services.surepay.hostname=http://localhost:9000/surepay/",
    "-J-Dmicroservice.services.surepay.enabled=true",
    "-J-DDev.auditing.consumer.baseUri.port=9000",
    "-J-DDev.auditing.consumer.baseUri.host=localhost",
    "-J-DDev.auditing.enabled=false",
    "-J-DProd.proxy.proxyRequiredForThisEnvironment=false",
    "-J-Dmicroservice.services.eiscd.aws.endpoint=http://localhost:8001",
    "-J-Dmicroservice.services.eiscd.aws.bucket=txm-dev-bacs-eiscd",
    "-J-Dmicroservice.services.eiscd.aws.accesskeyid=AKIAIOSFODNN7EXAMPLE",
    "-J-Dmicroservice.services.eiscd.aws.secretkey=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
    "-J-Dmicroservice.services.modcheck.aws.endpoint=http://localhost:8001",
    "-J-Dmicroservice.services.modcheck.aws.bucket=txm-dev-bacs-modcheck",
    "-J-Dmicroservice.services.modcheck.aws.accesskeyid=AKIAIOSFODNN7EXAMPLE",
    "-J-Dmicroservice.services.modcheck.aws.secretkey=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
    "-J-Dmicroservice.services.thirdPartyCache.endpoint=http://localhost:9899/cache",
    "-J-Dmicroservice.services.surepay.cache.enabled=true"
  ],
  "BANK_ACCOUNT_REPUTATION_THIRD_PARTY_CACHE": [
    "-J-Dcontrollers.confidenceLevel.uk.gov.hmrc.bankaccountreputationthirdpartycache.controllers.CacheController.needsLogging=true"
  ],
  "BANK_ACCOUNT_VERIFICATION_FRONTEND": [
    "-J-Dmicroservice.services.bank-account-reputation.url=http://localhost:9871/v2/validateBankDetails",
    "-J-Dauditing.consumer.baseUri.port=9000",
    "-J-Dauditing.consumer.baseUri.host=localhost",
    "-J-Dauditing.enabled=false",
    "-J-Dmicroservice.services.access-control.enabled=false"
  ]
}
'
```

To debug through the IDE you can invoke `LocalRunner`.  You may want to set `-Dperftest.runSmokeTest=True` in your VM Options to ensure the scenarios are only run once.
