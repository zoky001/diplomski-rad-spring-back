package hr.foi.diplomski.rad.client.repository

import hr.foi.diplomski.rad.abstracts.AbstractRepository
import hr.foi.diplomski.rad.model.client.Client
import hr.foi.diplomski.rad.model.exposure.Exposure

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.codebook.service.CodebookService
import hr.foi.diplomski.rad.core.model.CodebookEntry


@TypeChecked
@Repository
@Slf4j
class ClientRepository extends AbstractRepository<Client> {

    @Autowired
    Sql sql

    DataSource dataSource

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    private CodebookService codebookService

    @Autowired
    ClientRepository(
            @Qualifier(value = 'datasource-db2') DataSource dataSource, CodebookService codebookService) {
        this.dataSource = dataSource
        this.codebookService = codebookService
    }

    /**
     * findByOwnerId
     */
    List<Client> findByOwnerId(Long ownerId) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        List<Client> clients = new ArrayList<Client>()
        Client client
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KLIJENT(?)}")
            cs.setLong(1, ownerId)
            ResultSet rs = cs.executeQuery()
            while (rs.next()) {
                rs.getLong("ID_GRUPIRANJA")
                if (rs.wasNull()) {
                    client = mapLoadedClient(rs)
                    clients.add(client)
                }
            }
        } catch (SQLException e) {
            log.info "Greska kod dohvata klijenata za grupu sa IDjem $ownerId $e.message"
            throw new RispoException("Greska kod dohvata klijenata za grupu sa IDjem " + ownerId, e)
        } finally {
            closeConnection(connection)
        }
        clients
    }

    /**
     * getClientsForGroup
     * @param groupId
     * @return List<Client>
     * @throws RispoException
     */
    List<Client> getClientsForGroup(Long groupId) throws RispoException {
        List<Client> clients = findByOwnerId(groupId)
        for (Client client : clients) {
            if (client.getRegisterNumber() != null && !client.getRegisterNumber().isEmpty()) {
                try {
                    client.setVrstaOsobe(dohvatiVrstaOsobe(client.getRegisterNumber()))
                } catch (RispoException e) {
                    log.info "Greska kod dohvata vrste osobe za broj registra $client.registerNumber"
                }
            }
        }
        clients
    }

    /**
     * dohvatiVrstaOsobe
     * @param brRegistra
     * @return String* @throws RispoException
     */
    String dohvatiVrstaOsobe(String brRegistra) throws RispoException {

        CallableStatement cs = null
        Connection connection = null
        String vo = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call ZE" + schemaName + ".SP_VRSTA_KLIJENTA(?)}")
            cs.setString(1, brRegistra)
            ResultSet rs = cs.executeQuery()
            if (rs.next()) {
                vo = rs.getString(1)
            }
        } catch (SQLException e) {
            log.info "Greska kod dohvata vrste osobe sa brojem registra $brRegistra $e.message"
            throw new RispoException("Greska kod dohvata vrste osobe sa brojem registra $brRegistra", e)
        } finally {
            closeConnection(connection)
        }
        if (vo == null) {
            throw new RispoException("Nema vrste osobe sa brojem registra $brRegistra")
        }
        vo
    }

    /**
     * unGroup
     * @param client
     * @return List<Client>
     * @throws RispoException
     */
    List<Client> unGroup(Client client) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        List<Client> clients = new ArrayList<Client>()

        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KLIJENT(?)}")
            cs.setLong(1, client.getGroupId())
            ResultSet rs = cs.executeQuery()
            while (rs.next()) {
                Long groupedClientId = rs.getLong("ID_GRUPIRANJA")
                if (!rs.wasNull() && groupedClientId.equals(client.getId())) {
                    Client c = mapLoadedClient(rs)
                    c.setGroupedClientId(null)
                    clients.add(c)
                }
            }
            save(clients)
        } catch (SQLException e) {
            log.info "Greska kod ogdrupiravanja klijenta sa IDjem $client.id $e.message"
            throw new RispoException("Greska kod ogdrupiravanja klijenta sa IDjem " + client.getId(), e)
        } finally {
            closeConnection(connection)
        }
        clients
    }

    /**
     * mapLoadedClient
     * @param rs
     * @return Client* @throws RispoException
     */
    private Client mapLoadedClient(ResultSet rs) throws RispoException {
        Client client = new Client()
        try {
            Long id = rs.getLong("ID_KLIJENT")
            String regNumber = getGetTrimmedStringOrNull(rs, "BR_REGISTRA")
            String oib = getGetTrimmedStringOrNull(rs, "OIB")
            String jmbg = getGetTrimmedStringOrNull(rs, "JMBG")
            String mbr = getGetTrimmedStringOrNull(rs, "MATICNI_BROJ")
            String smbr = getGetTrimmedStringOrNull(rs, "PODBROJ")
            String borrower = getGetTrimmedStringOrNull(rs, "BORROWER")

            String sndg = getGetTrimmedStringOrNull(rs, "SNDG")
            String country = getGetTrimmedStringOrNull(rs, "COUNTRY")

            CodebookEntry intRating = getCodebookEntry(rs, "INT_RATING")
            BigDecimal pd = rs.getBigDecimal("PD")
            CodebookEntry ratingModel = getCodebookEntry(rs, "RATING_MODEL")
            boolean financialsEnclosed = getBoolean(rs, "FINANCIALS_ENCLOSED")
            String industry = getGetTrimmedStringOrNull(rs, "INDUSTRY")
            String ownerName = getGetTrimmedStringOrNull(rs, "OWNER_NAME")
            Double ownerShare = rs.getDouble("OWNER_SHARE")
            CodebookEntry ratingRelation = getCodebookEntry(rs, "RATING_RELATION")
            Long gId = rs.getLong("ID_GRUPA")
            String owner = getGetTrimmedStringOrNull(rs, "RADNIK_UNOS")
            boolean isGrouped = getBoolean(rs, "OZNAKA_GRUPIRANJA")
            Long groupedClientId = rs.getLong("ID_GRUPIRANJA")
            boolean primaryMember = getBoolean(rs, "PRIMARNI_CLAN")
            if (rs.wasNull()) {
                groupedClientId = null
            }
            boolean manualInput = getBoolean(rs, "RUCNI_UNOS")
            boolean includedInReport = !getBoolean(rs, "BEZ_PLASMANA")
            String orgJed = rs.getString("OJ_KLIJENT")
            BigDecimal intRateHRK = rs.getBigDecimal("KLIJENT_KAM_HRK")
            BigDecimal intRateEUR = rs.getBigDecimal("KLIJENT_KAM_EUR")

            BigDecimal feesHRK = rs.getBigDecimal("KLIJENT_NAK_HRK")
            BigDecimal feesEUR = rs.getBigDecimal("KLIJENT_NAK_EUR")

            boolean error = getBoolean(rs, "GRESKA_DOHVATA_IZLOZENOSTI")

            //int index = 0
            //int indexWithExposures = 0

            List<Exposure> exposures = null
            Exposure total = null
            // boolean selected = false
            // String vrstaOsobe = ""

            client = new Client(
                    id: id,
                    mb: mbr,
                    smb: smbr,
                    oib: oib,
                    jmbg: jmbg,
                    registerNumber: regNumber,
                    borrower: borrower,
                    country: country,
                    intRating: intRating,
                    ratingModel: ratingModel,
                    pd: pd,
                    financialsEnclosed: financialsEnclosed,
                    industry: industry,
                    ownerName: ownerName,
                    ownerShare: ownerShare,
                    index: 0,
                    indexWithExposures: 0,
                    sndg: sndg,
                    exposures: exposures,
                    total: total,
                    selected: false,
                    ratingRelation: ratingRelation,
                    groupId: gId,
                    owner: owner,
                    grouped: isGrouped,
                    groupedClientId: groupedClientId,
                    manualInput: manualInput,
                    includedInReport: includedInReport,
                    primaryMember: primaryMember,
                    orgJed: orgJed,
                    intRateHRK: intRateHRK,
                    intRateEUR: intRateEUR,
                    feesHRK: feesHRK,
                    feesEUR: feesEUR,
                    error: error,
                    vrstaOsobe: "")
        } catch (SQLException e) {
            log.info "Greska kod mapiranja klijenta $e.message"
            throw new RispoException("Greska kod mapiranja klijenta", e)
        }
        client
    }

    /**
     * delete
     */
    Client delete(Client entity) throws RispoException {

        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KLIJENT_DELETE(?)}")
            cs.setLong(1, entity.getId())
            cs.executeUpdate()
        } catch (SQLException e) {
            log.info "Greska kod brisanja klijenata sa IDjem $entity.id $e.message"
            throw new RispoException("Greska kod brisanja klijenata sa IDjem $entity.id $e.message", e)
        } finally {
            closeConnection(connection)
        }
        entity
    }

    /**
     * save
     */

    Client save(Client entity) throws RispoException {
        Client retObj
        if (entity.getId() == null) {
            retObj = callInsertProcedure(entity)
        } else {
            retObj = callUpdateProcedure(entity)
        }
        retObj
    }

    /**
     * save
     */
    @Override
    List<Client> save(List<Client> entities) throws RispoException {
        boolean error = false
        List<Client> retVal = new ArrayList<Client>()
        Client retObj
        for (Client entity : entities) {
            try {
                retObj = save(entity)
                retVal.add(retObj)
            } catch (RispoException e) {
                error = true
            }
        }
        if (error) {
            throw new RispoException("Greska kod spremanja klijenata")
        }
        retVal
    }

    /**
     * setPrimaryMember
     * @param c
     * @return boolean* @throws RispoException
     */
    boolean setPrimaryMember(Client c) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_PRIM_CLAN_AZUR(?,?)}")
            cs.setLong(1, c.getGroupId())
            cs.setLong(2, c.getId())
            cs.executeUpdate()
        } catch (SQLException e) {
            log.info "Greska kod postavljanja matice za klijenta sa IDjem $c.id $e.message"
            throw new RispoException("Greska kod postavljanja matice za klijenta sa IDjem $c.id $e.message", e)
        } finally {
            closeConnection(connection)
        }
        return true
    }

    /**
     * callUpdateProcedure
     * @param client
     * @throws RispoException
     */
    private Client callUpdateProcedure(Client client) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        // String countryCode
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KLIJENT_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")

            cs.setLong(1, client.getId())
            setStringWithNullCheck(cs, 2, client.getRegisterNumber())
            setStringWithNullCheck(cs, 3, client.getOib())
            setStringWithNullCheck(cs, 4, client.getJmbg())
            setStringWithNullCheck(cs, 5, client.getMb())
            setStringWithNullCheck(cs, 6, client.getSmb())
            setStringWithNullCheck(cs, 7, client.getBorrower())
            setStringWithNullCheck(cs, 8, client.getSndg())

            setStringWithNullCheck(cs, 9, client.getCountry() != null ? client.getCountry().substring(0, 2) : null)

            setCodebookEntryWithNullCheck(cs, 10, client.getIntRating())
            setBigDecimalWithNullCheck(cs, 11, client.getPd())
            setCodebookEntryWithNullCheck(cs, 12, client.getRatingModel())

            cs.setString(13, client.isFinancialsEnclosed() ? "Y" : "N")
            setStringWithNullCheck(cs, 14, client.getIndustry())
            setStringWithNullCheck(cs, 15, client.getOwnerName())
            setDoubleWithNullCheck(cs, 16, client.getOwnerShare())
            setCodebookEntryWithNullCheck(cs, 17, client.getRatingRelation())
            setLongWithNullCheck(cs, 18, client.getGroupId())
            setStringWithNullCheck(cs, 19, client.getOwner())
            cs.setString(20, client.isGrouped() ? "Y" : "N")

            if (client.getGroupedClientId() == null || client.getGroupedClientId() == 0) {
                cs.setNull(21, Types.INTEGER)
            } else {
                cs.setLong(21, client.getGroupedClientId())
            }

            //setLongWithNullCheck(cs, 21, client.getGroupedClientId())
            cs.setBigDecimal(22, client.getFeesHRK())// NAK HRK
            cs.setBigDecimal(23, client.getIntRateHRK())
            cs.setString(24, client.isManualInput() ? "Y" : "N")
            cs.setString(25, client.isIncludedInReport() ? "N" : "Y")
            cs.setString(26, client.getOrgJed())
            cs.setBigDecimal(27, client.getFeesEUR())// NAK EUR
            cs.setBigDecimal(28, client.getIntRateEUR())
            cs.setString(29, client.isPrimaryMember() ? "Y" : "N")

            cs.executeUpdate()
        } catch (SQLException e) {
            log.info "Greska kod azuriranja klijenta sa IDjem $client.id $e.message"
            throw new RispoException("Greska kod azuriranja klijenta sa IDjem $client.id $e.message", e)
        } finally {
            closeConnection(connection)
        }

        client
    }

    /**
     * callInsertProcedure
     * @param client
     * @throws RispoException
     */
    private Client callInsertProcedure(Client client) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KLIJENT_INSERT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")

            setStringWithNullCheck(cs, 1, client.getRegisterNumber())
            setStringWithNullCheck(cs, 2, client.getOib())
            setStringWithNullCheck(cs, 3, client.getJmbg())
            setStringWithNullCheck(cs, 4, client.getMb())
            setStringWithNullCheck(cs, 5, client.getSmb())
            setStringWithNullCheck(cs, 6, client.getBorrower())
            setStringWithNullCheck(cs, 7, client.getSndg())
            setStringWithNullCheck(cs, 8, client.getCountry() != null ? client.getCountry().substring(0, 2) : null)

            setCodebookEntryWithNullCheck(cs, 9, client.getIntRating())
            setBigDecimalWithNullCheck(cs, 10, client.getPd())
            setCodebookEntryWithNullCheck(cs, 11, client.getRatingModel())

            cs.setString(12, client.isFinancialsEnclosed() ? "Y" : "N")
            setStringWithNullCheck(cs, 13, client.getIndustry())
            setStringWithNullCheck(cs, 14, client.getOwnerName())
            setDoubleWithNullCheck(cs, 15, client.getOwnerShare())
            setCodebookEntryWithNullCheck(cs, 16, client.getRatingRelation())
            setLongWithNullCheck(cs, 17, client.getGroupId())
            setStringWithNullCheck(cs, 18, client.getOwner())
            cs.setString(19, client.isGrouped() ? "Y" : "N")
            setLongWithNullCheck(cs, 20, client.getGroupedClientId())
            cs.setBigDecimal(21, BigDecimal.ZERO)// NAK HRK
            cs.setBigDecimal(22, BigDecimal.ZERO)// KAM HRK
            cs.setString(23, "Y") //rucni_unos uvjek true za insert kroz app
            cs.setString(24, "N") //bez_plasmana uvjek false za insert kroz app
            cs.setNull(25, Types.VARCHAR)//OJ
            cs.setBigDecimal(26, BigDecimal.ZERO)// NAK EUR
            cs.setBigDecimal(27, BigDecimal.ZERO)// KAM EUR
            cs.setString(28, "N")

            ResultSet rs = cs.executeQuery()
            if (rs.next()) {
                client.setId(rs.getLong(1))
            }
        } catch (SQLException e) {
            log.info "Greska kod spremanja klijenta $client.borrower $e.message"
            throw new RispoException("Greska kod spremanja klijenta $client.borrower $e.message", e)
        } finally {
            closeConnection(connection)
        }

        client
    }

    /**
     * getCodebookEntry
     * @param rs
     * @param columnName
     * @return
     * @throws RispoException
     */
    private CodebookEntry getCodebookEntry(ResultSet rs, String columnName) throws RispoException {
        Integer id
        CodebookEntry retVal = null
        try {
            //NT
            if (rs == null || codebookService == null) {
                retVal = null
            } else {
                id = rs.getInt(columnName)
                if (id != null && id > 0) {
                    retVal = codebookService.getCodebookEntry(id)
                }
            }
        } catch (RispoException e) {
            log.info "Greska kod dohvata codebook entryja stupac $columnName sa id $id " + e.getMessage() + " rs " + rs + " service " + codebookService
            // throw new RispoException("Greska kod dohvata codebook entryja stupac $columnName sa id $id " + e.getMessage(), e)
        }
        retVal
    }
}
