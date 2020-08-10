
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
