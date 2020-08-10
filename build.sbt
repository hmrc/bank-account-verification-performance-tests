name := "bank-account-verification-performance-tests"

organization := "uk.gov.hmrc"

scalaVersion := "2.12.12"

version := "0.1.0"

resolvers ++= Seq(
  Resolver.bintrayRepo("hmrc", "releases"),
  "typesafe-releases" at "https://nexus-preview.tax.service.gov.uk/content/repositories/typesafe-releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.0" % "test,it",
  "uk.gov.hmrc" %% "performance-test-runner" % "3.5.0" % "test,it",
  "io.gatling" % "gatling-test-framework" % "2.3.1" % "test,it",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.1" % "test,it"
)

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:_",
  "-Xlint", "-Xmax-classfile-name", "100"
)

enablePlugins(GatlingPlugin)
