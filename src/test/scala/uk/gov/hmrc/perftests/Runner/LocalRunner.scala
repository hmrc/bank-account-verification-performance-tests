package uk.gov.hmrc.perftests.Runner

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import uk.gov.hmrc.perftests.BAVFrontend.Simulation

object LocalRunner {

  def main(args: Array[String]): Unit = {

    val simClass = classOf[Simulation].getName

    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)

    Gatling.fromMap(props.build)
  }

}
