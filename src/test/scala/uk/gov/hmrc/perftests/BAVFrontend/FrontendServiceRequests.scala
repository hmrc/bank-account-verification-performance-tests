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
import io.gatling.core.session.Expression
import io.gatling.commons.validation._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object FrontendServiceRequests extends ServicesConfiguration {

  private val webContextRoot: String = "/bank-account-verification"
  private val bankAccountVerificationURL: String = baseUrlFor("bank-account-verification-frontend") + webContextRoot

  private def sessionString(name: String): Expression[Any] = session => session(name).as[String].success
  private def paramValue(value: String): Expression[Any] = _ => value.success

  val startJourney: HttpRequestBuilder = {
    http("Start journey")
      .get(s"$bankAccountVerificationURL/start/#{journeyId}")
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
      .check(status.is(200))
  }

  val selectPersonalAccountType: HttpRequestBuilder = {
    http("Select bank account type")
      .post(s"$bankAccountVerificationURL/start/#{journeyId}")
      .formParam("csrfToken", sessionString("csrfToken"))
      .formParam("accountType", paramValue("personal"))
      .formParam("continue", paramValue(""))
      .check(status.is(303))
  }

  val selectBusinessAccountType: HttpRequestBuilder = {
    http("Select bank account type")
      .post(s"$bankAccountVerificationURL/start/#{journeyId}")
      .formParam("csrfToken", sessionString("csrfToken"))
      .formParam("accountType", paramValue("business"))
      .formParam("continue", paramValue(""))
      .check(status.is(303))
  }

  val verifyPersonalAccountDetails: HttpRequestBuilder = {
    http("Load personal account details screen")
      .get(s"$bankAccountVerificationURL/verify/personal/#{journeyId}")
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
      .check(status.is(200))

    http("Submit personal bank account details")
      .post(s"$bankAccountVerificationURL/verify/personal/#{journeyId}")
      .formParam("csrfToken", sessionString("csrfToken"))
      .formParam("accountName", sessionString("accountName"))
      .formParam("sortCode", sessionString("sortCode"))
      .formParam("accountNumber", sessionString("accountNumber"))
      .formParam("rollNumber", sessionString("rollNumber"))
      .formParam("continue", paramValue(""))
      .check(status.is(303))
  }

  val verifyBusinessAccountDetails: HttpRequestBuilder = {
    http("Load business account details screen")
      .get(s"$bankAccountVerificationURL/verify/business/#{journeyId}")
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
      .check(status.is(200))

    http("Submit business bank account details")
      .post(s"$bankAccountVerificationURL/verify/business/#{journeyId}")
      .formParam("csrfToken", sessionString("csrfToken"))
      .formParam("companyName", sessionString("companyName"))
      .formParam("companyRegistrationNumber", sessionString("companyRegistrationNumber"))
      .formParam("sortCode", sessionString("sortCode"))
      .formParam("accountNumber", sessionString("accountNumber"))
      .formParam("rollNumber", sessionString("rollNumber"))
      .formParam("continue", paramValue(""))
      .check(status.is(303))
  }

  val confirmBusinessAccountDetails: HttpRequestBuilder = {
    http("Load business account details screen")
      .get(s"$bankAccountVerificationURL/verify/business/#{journeyId}")
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
      .check(status.is(200))

    http("Submit business bank account details")
      .post(s"$bankAccountVerificationURL/verify/business/#{journeyId}")
      .formParam("csrfToken", sessionString("csrfToken"))
      .formParam("companyName", sessionString("companyName"))
      .formParam("companyRegistrationNumber", sessionString("companyRegistrationNumber"))
      .formParam("sortCode", sessionString("sortCode"))
      .formParam("accountNumber", sessionString("accountNumber"))
      .formParam("rollNumber", sessionString("rollNumber"))
      .formParam("continue", paramValue(""))
      .check(status.is(303))
  }

}
