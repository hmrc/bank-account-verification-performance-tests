
package uk.gov.hmrc.perftests.BAVFrontend

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.BAVFrontend.APIServiceRequests._
import uk.gov.hmrc.perftests.BAVFrontend.FrontendServiceRequests._

class Simulation extends PerformanceTestRunner {

  setup("bank-account-verification-frontend", "Standard account entry flows") withRequests(
    initializeJourneyPage,
    startJourney,
    selectAccountType,
    verifyAccountDetails,
    getCompletedJourneyData
  )

  runSimulation()
}
