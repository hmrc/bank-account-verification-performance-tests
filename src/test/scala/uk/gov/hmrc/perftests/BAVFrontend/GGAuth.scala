/*
 * Copyright 2021 HM Revenue & Customs
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
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef.{http, status, _}
import io.gatling.http.check.HttpCheck
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.models.AuthRequest
import uk.gov.hmrc.perftests.models.auth._

object GGAuth extends ServicesConfiguration {
  private val webContextRoot: String = "/bank-account-verification"
  private val bankAccountVerificationURL: String = baseUrlFor("bank-account-verification-frontend") + webContextRoot
  private val authAPILoginUrl: String = baseUrlFor("auth-login-api") + "/government-gateway/session/login"
  private val authStubUrl: String = baseUrlFor("auth-login-stub") + "/auth-login-stub/gg-sign-in"

  def saveBearerToken: CheckBuilder[HttpCheck, Response, Response, String] = {
    header("Authorization").saveAs("authToken")
  }

  def body: String = {
    AuthRequest(
      "${credId}",
      AffinityGroup.Individual,
      Some(ConfidenceLevel.L50),
      CredentialStrength.Weak,
      Some(CredentialRole.User),
      List.empty[Enrolment]
    ).asJsonString()
  }

  val apiAuthWithGovernmentGateway: HttpRequestBuilder =
    http("API auth with Government Gateway")
      .post(authAPILoginUrl)
      .header("Content-Type", "application/json")
      .body(StringBody(body))
      .check(status.is(201))
      .check(saveBearerToken)

  val frontendAuthWithGovernmentGateway: HttpRequestBuilder = {
    http("Website auth with Government Gateway")
      .post(authStubUrl)
      .formParam("authorityId", "${credId}")
      .formParam("redirectionUrl", s"$bankAccountVerificationURL/start/$${journeyId}")
      .formParam("credentialStrength", s"${CredentialStrength.Weak.name}")
      .formParam("confidenceLevel", s"${ConfidenceLevel.L50.level}")
      .formParam("affinityGroup", s"${AffinityGroup.Individual}")
//      .formParam("credentialRole", s"${CredentialRole.User}")
//      .formParam("email", "user@example.com")
      .check(status.is(303))
  }
}
