package hr.foi.diplomski.rad.client.mock.mappings

import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {
    configureFor("localhost", 10101)
    stubFor(get(urlMatching(".*/client/findByOwnerId.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/findByOwnerId.json")
    ))

    stubFor(get(urlMatching(".*/client/dohvatiVrstaOsobe.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/dohvatiVrstaOsobe.json")
    ))

    stubFor(get(urlMatching(".*/client/unGroup.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/unGroup.json")
    ))

    stubFor(get(urlMatching(".*/client/delete.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/delete.json")
    ))

    stubFor(get(urlMatching(".*/client/save.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/save.json")
    ))

    stubFor(get(urlMatching(".*/client/saveList.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/saveList.json")
    ))

    stubFor(get(urlMatching(".*/client/setPrimaryMember.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/setPrimaryMember.json")
    ))

    stubFor(get(urlMatching(".*/client/getClientsForGroup.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/client/getClientsForGroup.json")
    ))
}
