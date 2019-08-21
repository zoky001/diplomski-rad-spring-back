package hr.foi.diplomski.rad.sova.client.mock.mappings

import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {
    configureFor("localhost", 10101)
    stubFor(post(urlMatching(".*/SOVA.*")).atPriority(50)
            .withRequestBody(containing("wSKorisnikPOSifra"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/sova/client/wSKorisnikPOSifra.xml")
    ))


    stubFor(post(urlMatching(".*/SOVA.*")).atPriority(50)
            .withRequestBody(containing("wSKorisnikAutorizacija"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/sova/client/wSKorisnikAutorizacija.xml")
    ))


}
