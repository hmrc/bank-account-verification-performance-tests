package uk.gov.hmrc.perftests.models

import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.perftests.models.auth._

object AuthRequest {
  val DEFAULT_SERVICE_IDENTIFIER = "bavf-performance-test"
  implicit val initJsonFormat: OFormat[AuthRequest] = Json.format[AuthRequest]
}

case class AuthRequest(credId: String,
                       affinityGroup: AffinityGroup,
                       confidenceLevel: Option[ConfidenceLevel],
                       credentialStrength: CredentialStrength,
                       credentialRole: Option[CredentialRole],
                       enrolments: Seq[Enrolment]) {

  def asJsonString(): String = {
    Json.toJson(this).toString()
  }

}
