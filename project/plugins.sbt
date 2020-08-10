credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

addSbtPlugin("uk.gov.hmrc"  % "sbt-auto-build"  % "2.9.0")
addSbtPlugin("io.gatling" % "gatling-sbt" % "3.1.0")
