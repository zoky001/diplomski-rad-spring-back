package hr.foi.diplomski.rad.exposure.mock.mappings

import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {
    configureFor("localhost", 10101)

    stubFor(get(urlMatching(".*/exposure/findByOwnerId.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/exposure/findByOwnerId.json")
    ))
    stubFor(get(urlMatching(".*/exposure/findByGroupedOwnerId.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/exposure/findByGroupedOwnerId.json")
    ))
}