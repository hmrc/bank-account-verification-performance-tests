import sbt._

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.playframework" %% "play-json"                % "3.0.5",
    "uk.gov.hmrc"       %% "performance-test-runner"  % "6.2.0"
  ).map(_ % Test)
}
