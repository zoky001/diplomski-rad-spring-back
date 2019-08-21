package hr.foi.diplomski.rad.collateral.mock.mappings

import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {
    configureFor("localhost", 10101)
    stubFor(get(urlMatching(".*/collateral/findByOwnerId.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/collateral/findByOwnerId.json")
    ))

    stubFor(get(urlMatching(".*/collateral/delete.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/collateral/delete.json")
    ))

    stubFor(get(urlMatching(".*/collateral/save.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/collateral/save.json")
    ))

    stubFor(get(urlMatching(".*/collateral/saveList.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/collateral/saveList.json")
    ))
}
