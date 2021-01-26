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
