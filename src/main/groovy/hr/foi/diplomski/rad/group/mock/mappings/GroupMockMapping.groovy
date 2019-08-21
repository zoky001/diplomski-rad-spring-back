package hr.foi.diplomski.rad.group.mock.mappings


import static com.github.tomakehurst.wiremock.client.WireMock.*

static init() {



    configureFor("localhost", 10101)
    stubFor(get(urlMatching(".*/group/findOne.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findOne.json")
    ))

    stubFor(get(urlMatching(".*/group/findAll.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findAll.json")
    ))

    stubFor(get(urlMatching(".*/group/find.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/find.json")
    ))

    stubFor(get(urlMatching(".*/group/findLogsByGroup.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findLogsByGroup.json")
    ))

    stubFor(get(urlMatching(".*/group/findByOwnerAndStatus.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findByOwnerAndStatus.json")
    ))

    stubFor(get(urlMatching(".*/group/findByOwnerAndStatus.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findByOwnerAndStatus.json")
    ))

    stubFor(get(urlMatching(".*/group/getDistinctMembersForGroup.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/getDistinctMembersForGroup.json")
    ))

    stubFor(get(urlMatching(".*/group/findByKpoWithoutClients.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findByKpoWithoutClients.json")
    ))

    stubFor(get(urlMatching(".*/group/lock.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/lock.json")
    ))

    stubFor(get(urlMatching(".*/group/save.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/save.json")
    ))

    stubFor(get(urlMatching(".*/group/delete.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/delete.json")
    ))

    stubFor(get(urlMatching(".*/group/findByStatusAndDate.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findByStatusAndDate.json")
    ))

    stubFor(get(urlMatching(".*/group/updateOwner.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/updateOwner.json")
    ))

    stubFor(get(urlMatching(".*/group/findByStatusAndOrganizationalUnits.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/findByStatusAndOrganizationalUnits.json")
    ))

    stubFor(get(urlMatching(".*/group/groupContainsOrganizationalUnits.*")).atPriority(50)
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBodyFile("rispo/group/groupContainsOrganizationalUnits.json")
    ))

}
