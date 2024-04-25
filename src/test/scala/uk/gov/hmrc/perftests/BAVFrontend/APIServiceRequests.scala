/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.BAVFrontend

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.models.InitRequest

object APIServiceRequests extends ServicesConfiguration {

  private val userAgent = "bavfe-performance-tests"
  private val apiContextRoot = "/api"
  private val bankAccountVerificationAPI: String = baseUrlFor("bank-account-verification-frontend-api") + apiContextRoot

  val initializeJourneyPage: HttpRequestBuilder = {
    http("Initialise journey")
      .post(s"$bankAccountVerificationAPI/v3/init")
      .header(HttpHeaderNames.UserAgent, userAgent)
      .header(HttpHeaderNames.Authorization, "${authToken}")
      .header(HttpHeaderNames.ContentType, "application/json")
      .body(StringBody(InitRequest().asJsonString()))
      .check(status is 200)
      .check(jsonPath("$.journeyId").saveAs("journeyId"))
  }

  val getCompletedJourneyData: HttpRequestBuilder = {
    http("Collect journey data")
      .get(s"$bankAccountVerificationAPI/v3/complete/$${journeyId}")
      .header(HttpHeaderNames.Authorization, "${authToken}")
      .check(status is 200)
      .check(jsonPath("$").saveAs("response"))
  }
}
