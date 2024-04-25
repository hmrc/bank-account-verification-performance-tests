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

package uk.gov.hmrc.perftests.models

import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.perftests.models.InitRequest.DEFAULT_SERVICE_IDENTIFIER

object InitRequest {
  val DEFAULT_SERVICE_IDENTIFIER = "bavf-performance-test"
  implicit val initJsonFormat: OFormat[InitRequest] = Json.format[InitRequest]
}

case class InitRequest(serviceIdentifier: String = DEFAULT_SERVICE_IDENTIFIER,
                       continueUrl: String = s"https://www.staging.tax.service.gov.uk/bank-account-verification-example-frontend/done",
                       prepopulatedData: Option[PrepopulatedData] = None,
                       address: Option[Address] = None,
                       messages: Option[Messages] = None,
                       customisationsUrl: Option[String] = None,
                       bacsRequirements: Option[InitBACSRequirements] = Some(InitBACSRequirements(directDebitRequired = false, directCreditRequired = false)),
                       timeoutConfig: Option[InitRequestTimeoutConfig] = None) {

  def asJsonString(): String = {
    Json.toJson(this).toString()
  }

}
