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
                       customisationsUrl: Option[String] = None) {

  def asJsonString(): String = {
    Json.toJson(this).toString()
  }

}
