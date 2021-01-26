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

import java.util.UUID.randomUUID

object GGAuth extends ServicesConfiguration {
  private val authLoginUrl: String = baseUrlFor("auth-login-api") + "/government-gateway/session/login"

  def saveBearerToken: CheckBuilder[HttpCheck, Response, Response, String] = {
    header("Authorization").saveAs("bearerToken")
  }

  def body: String = {
    AuthRequest(
      randomUUID().toString.slice(0, 29),
      AffinityGroup.Individual,
      Some(ConfidenceLevel.L50),
      CredentialStrength.Weak,
      Some(CredentialRole.User),
      List.empty[Enrolment]
    ).asJsonString()
  }

  val authWithGovernmentGateway: HttpRequestBuilder = http("gg auth call")
    .post(authLoginUrl)
    .header("Content-Type", "application/json")
    .body(StringBody(body))
    .check(status.is(201))
    .check(saveBearerToken)
}
