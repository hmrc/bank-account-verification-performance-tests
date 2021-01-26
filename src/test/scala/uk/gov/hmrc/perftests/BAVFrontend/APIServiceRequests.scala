package uk.gov.hmrc.perftests.BAVFrontend

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.models.InitRequest

object APIServiceRequests extends ServicesConfiguration {

  private val apiContextRoot = "/api"
  private val bankAccountVerificationAPI: String = baseUrlFor("bank-account-verification-frontend-api") + apiContextRoot

  val initializeJourneyPage: HttpRequestBuilder = {
    http("Initialise journey")
      .post(s"$bankAccountVerificationAPI/init")
      .header("Authorization", "${bearerToken}")
      .header("Content-Type", "application/json")
      .body(StringBody(InitRequest().asJsonString()))
      .check(status is 200)
      .check(jsonPath("$.journeyId").saveAs("journeyId"))
  }

  val getCompletedJourneyData: HttpRequestBuilder = {
    http("Collect journey data")
      .get(s"$bankAccountVerificationAPI/complete/$${journeyId}")
      .header("Authorization", "${bearerToken}")
      .check(status is 200)
      .check(jsonPath("$").saveAs("response"))
  }
}
