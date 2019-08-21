package hr.foi.diplomski.rad.interest.rate.reference.mock.mappings

import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {
	configureFor("localhost", 10101)
		stubFor(get(urlMatching(".*/interestRateReference/getReferenceByName.*")).atPriority(50)
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Content-Type", "application/json;charset=UTF-8")
				.withBodyFile("rispo/interest/rate/reference/getReferenceByName.json")
		))

}

