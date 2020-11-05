package uk.gov.hmrc.perftests.models

import play.api.libs.json.{Json, OFormat}

object Address {
  implicit val addressJsonFormat: OFormat[Address] = Json.format[Address]
}

case class Address(lines: List[String],
                   town: Option[String] = None,
                   postcode: Option[String] = None) {
}
