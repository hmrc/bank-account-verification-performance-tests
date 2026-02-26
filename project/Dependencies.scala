import sbt._

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.playframework" %% "play-json"                % "3.0.6",
    "uk.gov.hmrc"       %% "performance-test-runner"  % "6.3.0"
  ).map(_ % Test)
}
