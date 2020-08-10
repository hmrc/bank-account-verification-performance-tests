package uk.gov.hmrc.perftests.BAVFrontend

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object FrontendServiceRequests extends ServicesConfiguration {

  private val webContextRoot: String = "/bank-account-verification"
  private val bankAccountVerificationURL: String = baseUrlFor("bank-account-verification-frontend") + webContextRoot

  val startJourney: HttpRequestBuilder = {
    http("Start journey")
      .get(s"$bankAccountVerificationURL/start/$${journeyId}")
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
      .check(status.is(200))
  }

  val verifyAccountDetails: HttpRequestBuilder = {
    http("Submit bank account details")
      .post(s"$bankAccountVerificationURL/verify/$${journeyId}")
      .formParam("csrfToken", "${csrfToken}")
      .formParam("accountName", "${accountName}")
      .formParam("sortCode", "${sortCode}")
      .formParam("accountNumber", "${accountNumber}")
      .formParam("rollNumber", "${rollNumber}")
      .formParam("continue", "")
      .check(status.is(303))
    //TODO add in when we have an expected location
    //.check(header("Location").is(s"$expectedLocation": String))
  }

}
