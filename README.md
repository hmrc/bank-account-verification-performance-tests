
# Bank Account Verification performance tests

Performance test repository fo the bank-account-verification services

### Current Volumetrics (JPS config)

> Last checked/updated: **2026-02-24**
>
> Based on a profile of the service in production over the past year, the max requests/minute is ~20.

The following configuration of journeys per second (JPS) results in around ~400 requests/minute, which is a good level for testing the performance of the service without overwhelming it. It also provides us with more than enough headroom.

- Business Verification: **0.8 JPS**
- Personal Verification: **0.5 JPS**
   
## Running tests   
    
### Smoke test

To run one journey with one user
```shell
  sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

### Run the performance test (Default)
```shell
  sbt gatling:test
```
## Running locally

When running locally, the `bank-account-reputation-stub` is used to simulate BARS responses. In staging, this will use the proper service and separate test data.

First of all you will need to spin up an environment that can be tested locally using the following script:
```shell
    ./start_services.sh
```

**Note:** sm2 will say that `BANK_ACCOUNT_REPUTATION` failed to start. This is expected, because locally we use `bank-account-reputation-stub` instead of the real service.

Then to run the tests, you need to use the `./run-local.sh` script. This is important, as it specifies separate local journeys which use separate feeder files for the test data.


## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").