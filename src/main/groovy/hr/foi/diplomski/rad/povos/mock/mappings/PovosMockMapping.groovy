package hr.foi.diplomski.rad.povos.mock.mappings

import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {
    configureFor("localhost", 10101)
    stubFor(get(urlMatching(".*/povos/dohvatiGrupuPrim.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/povos/client/dohvatiGrupuPrim.json")
    ))

}

