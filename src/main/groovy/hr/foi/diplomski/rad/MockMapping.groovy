package hr.foi.diplomski.rad

class MockMapping {
    private static def sampleMap = [
            "/group/findOne": 'group/findOne.json',
            "/group/findByOwnerAndStatus": 'group/findByOwnerAndStatus.json',
            "/group/findByStatusAndDate": 'group/findByStatusAndDate.json',
            "/group/findLogsByGroup": 'group/findLogsByGroup.json',
            "/group/delete": 'group/delete.json',
            "/group/find": 'group/find.json',
            "/group/findAll": 'group/findAll.json',
            "/group/getDistinctMembersForGroup": 'group/getDistinctMembersForGroup.json',
            "/group/findByKpoWithoutClients": 'group/findByKpoWithoutClients.json',
            "/group/lock": 'group/lock.json',
            "/group/save": 'group/save.json',
            "/group/updateOwner": 'group/updateGroupOwner.json',
            "/group/findByStatusAndOrganizationalUnits": 'group/findByStatusAndOrganizationalUnits.json',
            "/group/groupContainsOrganizationalUnits": 'group/groupContainsOrganizationalUnits.json',

            "/codebook/loadEntries": 'codebook/loadEntries.json',
            "/codebook/delete": 'codebook/delete.json',
            "/codebook/save": 'codebook/save.json',

            "/creditType/loadTypeOfCreditData": 'creditType/loadTypeOfCreditData.json',
            "/creditType/delete": 'creditType/delete.json',
            "/creditType/save": 'creditType/save.json',

            "/interestRateReference/getEntries": 'interest/rate/reference/getEntries.json',

            "/placementType/loadEntries": 'placementType/loadEntries.json',
            "/placementType/delete": 'placementType/delete.json',
            "/placementType/save": 'placementType/save.json',


            "/client/getClientsForGroup": 'client/getClientsForGroup.json',
            "/client/findByOwnerId": 'client/findByOwnerId.json',
            "/client/dohvatiVrstaOsobe": 'client/dohvatiVrstaOsobe.json',
            "/client/delete": 'client/delete.json',
            "/client/save": 'client/save.json',
            "/client/saveList": 'client/saveList.json',
            "/client/unGroup": 'client/unGroup.json',
            "/client/setPrimaryMember": 'client/setPrimaryMember.json',


            "/exposure/findByOwnerId": 'exposure/findByOwnerId.json',
            "/exposure/findByGroupedOwnerId": 'exposure/findByGroupedOwnerId.json',
            "/exposure/delete": 'exposure/delete.json',
            "/exposure/save": 'exposure/save.json',
            "/exposure/saveList": 'exposure/save.json',
            "/exposure/unGroup": 'exposure/ungroup.json',
            "/exposure/unGroupExposure": 'exposure/ungroup.json',


            "/collateral/findByOwnerId": 'collateral/findByOwnerId.json',
            "/collateral/delete": 'collateral/delete.json',
            "/collateral/save": 'collateral/save.json',
            "/collateral/saveList": 'collateral/saveList.json'


    ]

    static String getJsonPath(String url) {

        MockMapping.sampleMap[url]
    }
}
