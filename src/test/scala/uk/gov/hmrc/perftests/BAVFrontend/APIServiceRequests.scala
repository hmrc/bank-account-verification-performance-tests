package uk.gov.hmrc.perftests.BAVFrontend

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object APIServiceRequests extends ServicesConfiguration {

  private val apiContextRoot = "/api"
  private val initialConfiguration = "{}"
  private val bankAccountVerificationAPI: String = baseUrlFor("bank-account-verification-frontend") + apiContextRoot

  val initializeJourneyPage: HttpRequestBuilder = {
    http("Initialise journey")
      .post(s"$bankAccountVerificationAPI/init")
      .header("Content-Type", "application/json")
      .body(StringBody(initialConfiguration))
      .check(status is 200)
      .check(jsonPath("$").saveAs("journeyId"))
  }

  val getCompletedJourneyData: HttpRequestBuilder = {
    http("Collect journey data")
      .get(s"$bankAccountVerificationAPI/complete/$${journeyId}")
      .check(status is 200)
      .check(jsonPath("$").saveAs("response"))
  }
}
