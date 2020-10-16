
package uk.gov.hmrc.perftests.BAVFrontend

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.BAVFrontend.APIServiceRequests._
import uk.gov.hmrc.perftests.BAVFrontend.FrontendServiceRequests._

class Simulation extends PerformanceTestRunner {

  setup("bank-account-verification-frontend-personal", "Personal account entry flows") withRequests(
    initializeJourneyPage,
    startJourney,
    selectPersonalAccountType,
    verifyPersonalAccountDetails,
    getCompletedJourneyData
  )

  setup("bank-account-verification-frontend-business", "Business account entry flows") withRequests(
    initializeJourneyPage,
    startJourney,
    selectBusinessAccountType,
    verifyBusinessAccountDetails,
    getCompletedJourneyData
  )

  runSimulation()
}
