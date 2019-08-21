package hr.foi.diplomski.rad.exposure.repository

import hr.foi.diplomski.rad.interest.rate.reference.repository.InterestRateReferenceRepository
import hr.foi.diplomski.rad.interest.rate.reference.service.InterestRateReferenceService

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.util.Map.Entry

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.exposure.Collateral
import hr.foi.diplomski.rad.model.exposure.Exposure
import hr.foi.diplomski.rad.model.exposure.InterestRateReference
import hr.foi.diplomski.rad.abstracts.AbstractRepository
import hr.foi.diplomski.rad.codebook.service.CodebookService
import hr.foi.diplomski.rad.core.model.CodebookEntry

@TypeChecked
@Repository
@Slf4j
class ExposureRepository extends AbstractRepository<Exposure> {

    @Autowired
    private CodebookService codebookService

    @Autowired
    private InterestRateReferenceService interestRateReferenceService

    @Autowired
    InterestRateReferenceRepository interestRateReferenceRepository

    @Autowired
    Sql sql

    DataSource dataSource

    @Autowired
    ExposureRepository(
            @Qualifier(value = 'datasource-db2') DataSource dataSource, CodebookService codebookService) {
        this.dataSource = dataSource
        this.codebookService = codebookService
    }

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    @Override
    /**
     * findByOwnerId
     */
    List<Exposure> findByOwnerId(Long ownerId) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        List<Exposure> exposures = new ArrayList<Exposure>() // spremaju se plasmani koji nisu ni iz okvira ni iz limita
        List<Exposure> limitExposures = new ArrayList<Exposure>() // spremaju se plasmani limita
        List<Exposure> okvirExposures = new ArrayList<Exposure>() // spremaju se plasmani okvira
        Map<String, List<Exposure>> exposuresFromLimit = new HashMap<String, List<Exposure>>()
// Spremaju se  plasmani  koji  su  iz  limita  i  okvira  po  broju  okvira/limita
        Map<String, List<Exposure>> exposuresFromOkvir = new HashMap<String, List<Exposure>>()
// Spremaju se  plasmani  koji  su  iz  limita  i  okvira  po  broju  okvira/limita
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST(?)}")
            cs.setLong(1, ownerId)
            ResultSet rs = cs.executeQuery()

            Exposure exposure
            Long idGrupiranja

            while (rs.next()) {
                idGrupiranja = null
                idGrupiranja = rs.getLong("ID_GRUPIRANJA")
                if (rs.wasNull()) {
                    exposure = new Exposure()
                    exposure = mapLoadedExposure(rs)

                    log.info "dodajem izlozenost ownerId: $ownerId redak: "

                    if (!exposure.isDisplayed())
                        continue

                    if ("LIMOK".equals(exposure.getSource())) {
                        if (exposure.getTip() == 0) {
                            //LIMIT
                            limitExposures.add(exposure)
                        } else {
                            //OKVIR
                            okvirExposures.add(exposure)
                        }
                    } else {
                        if (exposure.getBrojOkvira() != null) {//iz okvira
                            if (exposuresFromOkvir.containsKey(exposure.getBrojOkvira())) {
                                exposuresFromOkvir.get(exposure.getBrojOkvira()).add(exposure)
                            } else {
                                List<Exposure> e = new ArrayList<Exposure>()
                                e.add(exposure)
                                exposuresFromOkvir.put(exposure.getBrojOkvira(), e)
                            }
                        } else if (exposure.getBrojLimita() != null) {//iz limita
                            if (exposuresFromLimit.containsKey(exposure.getBrojLimita())) {
                                exposuresFromLimit.get(exposure.getBrojLimita()).add(exposure)
                            } else {
                                List<Exposure> e = new ArrayList<Exposure>()
                                e.add(exposure)
                                exposuresFromLimit.put(exposure.getBrojLimita(), e)
                            }
                        } else {
                            exposures.add(exposure)
                        }
                    }
                }
            }

            //Dodaj iz limita trenutnog klijenta
            for (Exposure e : limitExposures) {
                exposures.add(e)
                if (exposuresFromLimit.containsKey(e.getBrojPartije()))
                    exposures.addAll(exposuresFromLimit.remove(e.getBrojPartije()))
            }
            //Dodaj iz limita drugog klijenta
            for (Entry<String, List<Exposure>> set : exposuresFromLimit.entrySet()) {
                exposures.addAll(set.getValue())
            }
            //Dodaj iz okvira trenutnog klijenta
            for (Exposure e : okvirExposures) {
                exposures.add(e)
                if (exposuresFromOkvir.containsKey(e.getBrojPartije()))
                    exposures.addAll(exposuresFromOkvir.remove(e.getBrojPartije()))
            }
            //Dodaj iz okvira drugog klijenta
            for (Entry<String, List<Exposure>> set : exposuresFromOkvir.entrySet()) {
                exposures.addAll(set.getValue())
            }
            return exposures
        } catch (Exception e) {
            log.error "Greska kod ucitavanja izlozenosti za klijenta $ownerId, $e.message"
            throw new RispoException("Greska kod ucitavanja izlozenosti za klijenta $ownerId, $e.message", e)
        } finally {
            closeConnection(connection)
        }

    }

    /**
     * unGroup
     * @param exposure
     * @param clientId
     * @return
     * @throws RispoException
     */
    boolean unGroup(Exposure exposure, Long clientId) throws RispoException {

        CallableStatement cs = null
        Connection connection = null
        List<Exposure> exposures = new ArrayList<Exposure>()

        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST(?)}")
            cs.setLong(1, clientId)
            ResultSet rs = cs.executeQuery()
            Long groupedExposureId
            while (rs.next()) {
                groupedExposureId = rs.getLong("ID_GRUPIRANJA")
                if (groupedExposureId != null && groupedExposureId.equals(exposure.getId())) {
                    Exposure e = mapLoadedExposure(rs)
                    e.setGroupedExposureId(null)
                    exposures.add(e)
                }
            }
            save(exposures)
            delete(exposure)
        } catch (Exception e) {
            throw new RispoException("Greska kod odgrupiravanja izlozenosti sa IDjem " + exposure.getId(), e)
        } finally {
            closeConnection(connection)
        }
        return true
    }

    /**
     * unGroup
     * @param clientId
     * @return
     * @throws RispoException
     */
    boolean unGroup(Long clientId) throws RispoException {

        CallableStatement cs = null
        Connection connection = null
        List<Exposure> exposures = new ArrayList<Exposure>()

        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST_GRUPIRANO(?)}")
            cs.setLong(1, clientId)
            ResultSet rs = cs.executeQuery()

            Exposure exposure
            while (rs.next()) {
                exposure = mapLoadedExposure(rs)
                exposure.setGroupedClientId(null)
                exposures.add(exposure)
            }
            save(exposures)
        } catch (Exception e) {
            throw new RispoException("Greska kod odgrupiravanja izlozenosti za klijenata sa IDjem " + clientId, e)
        } finally {
            closeConnection(connection)
        }
        return true
    }

    /**
     * mapLoadedExposure
     * @param rs
     * @return
     * @throws RispoException
     */
    private Exposure mapLoadedExposure(ResultSet rs) throws RispoException {
        Long id
        try {
            id = rs.getLong("ID_IZLOZENOST")
            String source = getGetTrimmedStringOrNull(rs, "SOURCE1")
            String typeOfCredit = getGetTrimmedStringOrNull(rs, "TYPE_OF_CREDIT")
            // String tenor1 = rs.getString("TENOR")
            String tenor = getGetTrimmedStringOrNull(rs, "TENOR")
            BigDecimal previousHrk = rs.getBigDecimal("PREVIOUS_HRK")
            // NT
            // Some of the records in the database have null here
            if (previousHrk == null) {
                log.error("izlozenost s ID_IZLOZENOST=" + id + " ima null na PREVIOUS_HRK")
                previousHrk = new BigDecimal(0)
            }

            CodebookEntry plasmanType = getCodebookEntry(rs, "TYPE1")

            BigDecimal changeHrk = rs.getBigDecimal("CHANGE_HRK")
            BigDecimal balanceHrk = rs.getBigDecimal("BALANCE_HRK")
            BigDecimal previousEur = rs.getBigDecimal("PREVIOUS_EUR")
            BigDecimal changeEur = rs.getBigDecimal("CHANGE_EUR")
            BigDecimal balanceEur = rs.getBigDecimal("BALANCE_EUR")
            BigDecimal spread = rs.getBigDecimal("SPREAD")
            String intRate = getGetTrimmedStringOrNull(rs, "CONDITIONS_INT_RATE")
            String fees = getGetTrimmedStringOrNull(rs, "CONDITIONS_FEES")

            boolean commited = getBoolean(rs, "COMMITTED1")
            CodebookEntry taker = getCodebookEntry(rs, "TAKER")

            Long cId = rs.getLong("ID_KLIJENT")
            String owner = getGetTrimmedStringOrNull(rs, "RADNIK_UNOS")

            CodebookEntry riskClass = getCodebookEntry(rs, "RISK_CLASS")

            String partija = getGetTrimmedStringOrNull(rs, "BROJ_PARTIJE")
            String ugovor = getGetTrimmedStringOrNull(rs, "BROJ_UGOVORA")
            String limit = getGetTrimmedStringOrNull(rs, "BROJ_LIMITA")
            String okvir = getGetTrimmedStringOrNull(rs, "BROJ_OKVIRA")

            // BigDecimal actualBLO = rs.getBigDecimal("ACTUAL_BLO")
            // BigDecimal balanceBLO = rs.getBigDecimal("BALANCE_BLO")

            boolean isGrouped = getBoolean(rs, "OZNAKA_GRUPIRANJA")
            Long groupedExposureId = rs.getLong("ID_GRUPIRANJA")
            if (rs.wasNull()) {
                groupedExposureId = null
            }
            Long groupedClientId = rs.getLong("ID_GRUPIRANOG_KLIJENTA")
            if (rs.wasNull()) {
                groupedClientId = null
            }

            String kamatnaStopa = rs.getString("KAMATNA_STOPA_OPIS")
            InterestRateReference intRateReference = null
            ServiceResult interestRateReferenceServiceResult
            if (kamatnaStopa != null) {
                interestRateReferenceServiceResult = interestRateReferenceService.getReferenceByName(kamatnaStopa.trim())
                intRateReference = (InterestRateReference) interestRateReferenceServiceResult.getResult()
            }

            boolean lessThanYear = getBoolean(rs, "LESS_THAN_YEAR")
            BigDecimal proposedHrk = rs.getBigDecimal("PROPOSED_HRK")
            BigDecimal securedBalanceHrk = rs.getBigDecimal("SECURED_BALANCE_HRK")
            BigDecimal securedPreviousHrk = rs.getBigDecimal("SECURED_PREVIOUS_HRK")
            BigDecimal securedProposedHrk = rs.getBigDecimal("SECURED_PROPOSED_HRK")

            BigDecimal proposedEur = rs.getBigDecimal("PROPOSED_EUR")
            BigDecimal securedBalanceEur = rs.getBigDecimal("SECURED_BALANCE_EUR")
            BigDecimal securedPreviousEur = rs.getBigDecimal("SECURED_PREVIOUS_EUR")
            BigDecimal securedProposedEur = rs.getBigDecimal("SECURED_PROPOSED_EUR")
            int vrsta = rs.getInt("VRSTA")

            List<Collateral> collaterals = null

            return new Exposure(id: id, brojPartije: partija, brojUgovora: ugovor, brojLimita: limit, brojOkvira: okvir, balanceHrk: balanceHrk,
                    balanceEur: balanceEur, changeHrk: changeHrk, changeEur: changeEur, collaterals: collaterals, commited: commited, interestRate: intRateReference,
                    intRate: intRate, fees: fees, lessThanYear: lessThanYear, plasmanType: plasmanType,
                    previousHrk: previousHrk, previousEur: previousEur, proposedHrk: proposedHrk, proposedEur: proposedEur, riskClass: riskClass,
                    securedBalanceHrk: securedBalanceHrk, securedBalanceEur: securedBalanceEur, securedPreviousHrk: securedPreviousHrk,
                    securedPreviousEur: securedPreviousEur, securedProposedHrk: securedProposedHrk,
                    securedProposedEur: securedProposedEur, source: source, spread: spread, taker: taker, tenor: tenor, typeOfCredit: typeOfCredit,
                    selected: false, isNew: false, clientId: cId, owner: owner, grouped: isGrouped, groupedExposureId: groupedExposureId,
                    groupedClientId: groupedClientId, tip: vrsta, newChange: new BigDecimal(0),
                    newBalance: new BigDecimal(0), newPrevious: new BigDecimal(0), newProposed: new BigDecimal(0),
                    newSecuredBalance: new BigDecimal(0), newSecuredPrevious: new BigDecimal(0), newSecuredProposed: new BigDecimal(0))

        } catch (RispoException e) {
            log.info "Greska kod mapiranja izlozenosti $e.message"
            throw new RispoException("Greska kod mapiranja izlozenosti $e.message", e)
        }
    }

    /**
     * findByGroupedOwnerId
     * @param ownerId
     * @return List<Exposure>
     * @throws RispoException
     */
    List<Exposure> findByGroupedOwnerId(Long ownerId) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        List<Exposure> exposures = new ArrayList<Exposure>() // spremaju se plasmani koji nisu ni iz okvira ni iz limita
        List<Exposure> limitExposures = new ArrayList<Exposure>() // spremaju se plasmani LIMOKa
        Map<String, List<Exposure>> exposuresFromLIMOK = new HashMap<String, List<Exposure>>()
// Spremaju se  plasmani  koji  su  iz  limita  i  okvira  po  broju  okvira/limita

        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST_GRUPIRANO(?)}")
            cs.setLong(1, ownerId)
            ResultSet rs = cs.executeQuery()

            Exposure exposure
            Long idGrupiranja
            while (rs.next()) {
                idGrupiranja = rs.getLong("ID_GRUPIRANJA");
                if (rs.wasNull()) {
                    exposure = mapLoadedExposure(rs)

                    if (!exposure.isDisplayed())
                        continue

                    if (null != exposure.getSource() && exposure.getSource().equals("LIMOK")) {
                        limitExposures.add(exposure)
                    } else {
                        if (exposure.getBrojOkvira() != null) {
                            if (exposuresFromLIMOK.containsKey(exposure.getBrojOkvira())) {
                                exposuresFromLIMOK.get(exposure.getBrojOkvira()).add(exposure)
                            } else {
                                List<Exposure> e = new ArrayList<Exposure>()
                                e.add(exposure)
                                exposuresFromLIMOK.put(exposure.getBrojOkvira(), e)
                            }
                        } else if (exposure.getBrojLimita() != null) {
                            if (exposuresFromLIMOK.containsKey(exposure.getBrojLimita())) {
                                exposuresFromLIMOK.get(exposure.getBrojLimita()).add(exposure)
                            } else {
                                List<Exposure> e = new ArrayList<Exposure>()
                                e.add(exposure)
                                exposuresFromLIMOK.put(exposure.getBrojLimita(), e)
                            }
                        } else {
                            exposures.add(exposure)
                        }
                    }
                }
            }
            for (Exposure e : limitExposures) {
                if (e != null) {
                    exposures.add(e)
                    if (exposuresFromLIMOK != null && e.getBrojPartije() != null && exposuresFromLIMOK.get(e.getBrojPartije()) != null) {
                        exposures.addAll(exposuresFromLIMOK.get(e.getBrojPartije()))
                    }
                }
            }
            return exposures
        } catch (Exception e) {
            log.info "Greska kod dohvacanja izlozenosti za grupiranog klijenta sa IDjem $ownerId $e.message"
            throw new RispoException("Greska kod dohvacanja izlozenosti za grupiranog klijenta sa IDjem" + ownerId, e)
        } finally {
            closeConnection(connection)
        }

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
                log.info "ExposureRepository.getCodebookEntry rs ili codebook service je null za stupac $columnName"
            } else {
                id = rs.getInt(columnName).toInteger()
                if (id != null && id > 0) {
                    retVal = codebookService.getCodebookEntry(id)
                    log.info "ExposureRepositorydohvacen codebook entry stupac $columnName id $id vrijednost $retVal.name"
                } else {
                    log.info "ExposureRepositoryid codebook entry stupac $columnName je null i vrijednosti $id"
                }
            }
        } catch (RispoException e) {
            log.info "ExposureRepository Greska kod dohvata codebook entryja stupac $columnName sa id $id " + e.message + " rs " + rs + " service " + codebookService
        }

        retVal
    }

    /**
     * delete
     */
    @Override
    Exposure delete(Exposure entity) throws RispoException {

        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST_DELETE(?)}")

            cs.setLong(1, entity.getId())
            cs.executeUpdate()
        } catch (Exception e) {
            log.info "Greska kod brisanja plasmana sa IDjem $entity.id $e.message"
            throw new RispoException("Greska kod brisanja plasmana sa IDjem $entity.id $e.message", e)
        } finally {
            closeConnection(connection)
        }
        entity
    }

    /**
     * save
     */
    @Override
    Exposure save(Exposure entity) throws RispoException {
        Exposure retVal
        Long id = entity.id
        if (id == null) {
            retVal = callInsertProcedure(entity)
        } else {
            retVal = callUpdateProcedure(entity)
        }
        retVal
    }

    /**
     * save
     */
    List<Exposure> save(List<Exposure> entities) throws RispoException {
        List<Exposure> retVal = new ArrayList()
        Exposure retObj
        boolean error = false
        for (Exposure entity : entities) {
            try {
                retObj = save(entity)
                retVal.add(retObj)
            } catch (RispoException e) {
                error = true
            }
        }
        if (error) {
            log.info "Greska kod spremanja nekih izlozenosti"
            throw new RispoException("Greska kod spremanja nekih izlozenosti")
        }
        retVal
    }

    /**
     * callUpdateProcedure
     * @param exposure
     * @throws RispoException
     */
    private Exposure callUpdateProcedure(Exposure exposure) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")

            cs.setLong(1, exposure.getId())
            setStringWithNullCheck(cs, 2, exposure.getSource())
            setStringWithNullCheck(cs, 3, exposure.getTypeOfCredit())
            setStringWithNullCheck(cs, 4, exposure.getTenor())
            setBigDecimalWithNullCheck(cs, 5, exposure.getPreviousHrk())
            setBigDecimalWithNullCheck(cs, 6, exposure.getChangeHrk())
            setBigDecimalWithNullCheck(cs, 7, exposure.getSpread())

            setInterestRateWithNullCheck(cs, 8, exposure.getInterestRate())
            setStringWithNullCheck(cs, 9, exposure.getFees())
            setCodebookEntryWithNullCheck(cs, 10, exposure.getPlasmanType())
            cs.setString(11, exposure.isCommited() ? "Y" : "N")
            setCodebookEntryWithNullCheck(cs, 12, exposure.getTaker())
            setCodebookEntryWithNullCheck(cs, 13, exposure.getRiskClass())

            setLongWithNullCheck(cs, 14, exposure.getClientId())
            setStringWithNullCheck(cs, 15, exposure.getOwner())

            setStringWithNullCheck(cs, 16, exposure.getBrojPartije())
            setStringWithNullCheck(cs, 17, exposure.getBrojUgovora())
            setStringWithNullCheck(cs, 18, exposure.getBrojLimita())
            setStringWithNullCheck(cs, 19, exposure.getBrojOkvira())

            cs.setString(20, exposure.getInterestRate() != null ? exposure.getInterestRate().getName() : null)
            cs.setString(21, exposure.isGrouped() ? "Y" : "N")
            setLongWithNullCheck(cs, 22, exposure.getGroupedExposureId())
            setLongWithNullCheck(cs, 23, exposure.getGroupedClientId())
            cs.setBigDecimal(24, exposure.getBalanceHrk())
            cs.setBigDecimal(25, exposure.getSecuredPreviousHrk())
            cs.setBigDecimal(26, exposure.getSecuredProposedHrk())
            cs.setBigDecimal(27, exposure.getSecuredBalanceHrk())
            cs.setString(28, exposure.isLessThanYear() ? "Y" : "N")
            cs.setBigDecimal(29, exposure.getProposedHrk())
            cs.setInt(30, exposure.getTip())
            setBigDecimalWithNullCheck(cs, 31, exposure.getPreviousEur())
            setBigDecimalWithNullCheck(cs, 32, exposure.getChangeEur())
            cs.setBigDecimal(33, exposure.getBalanceEur())
            cs.setBigDecimal(34, exposure.getSecuredPreviousEur())
            cs.setBigDecimal(35, exposure.getSecuredProposedEur())
            cs.setBigDecimal(36, exposure.getSecuredBalanceEur())
            setBigDecimalWithNullCheck(cs, 37, exposure.getPreviousEur())

            cs.executeUpdate()
        } catch (Exception e) {
            log.info "Greska kod azuriranja plasmana sa IDjem $exposure.id $e.message"
            throw new RispoException("Greska kod azuriranja plasmana sa IDjem $exposure.id $e.message", e)
        } finally {
            closeConnection(connection)
        }
        exposure
    }

    /**
     * callInsertProcedure
     * @param exposure
     * @throws RispoException
     */
    private Exposure callInsertProcedure(Exposure exposure) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_IZLOZENOST_INSERT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")

            setStringWithNullCheck(cs, 1, exposure.getSource())
            setStringWithNullCheck(cs, 2, exposure.getTypeOfCredit())
            setStringWithNullCheck(cs, 3, exposure.getTenor())
            setBigDecimalWithNullCheck(cs, 4, exposure.getPreviousHrk())
            setBigDecimalWithNullCheck(cs, 5, exposure.getChangeHrk())
            setBigDecimalWithNullCheck(cs, 6, exposure.getSpread())

            setInterestRateWithNullCheck(cs, 7, exposure.getInterestRate())
            setStringWithNullCheck(cs, 8, exposure.getFees())
            setCodebookEntryWithNullCheck(cs, 9, exposure.getPlasmanType())
            cs.setString(10, exposure.isCommited() ? "Y" : "N")
            setCodebookEntryWithNullCheck(cs, 11, exposure.getTaker())
            setCodebookEntryWithNullCheck(cs, 12, exposure.getRiskClass())

            setLongWithNullCheck(cs, 13, exposure.getClientId())
            setStringWithNullCheck(cs, 14, exposure.getOwner())

            setStringWithNullCheck(cs, 15, exposure.getBrojPartije())
            setStringWithNullCheck(cs, 16, exposure.getBrojUgovora())
            setStringWithNullCheck(cs, 17, exposure.getBrojLimita())
            setStringWithNullCheck(cs, 18, exposure.getBrojOkvira())

            cs.setString(19, exposure.getInterestRate() != null ? exposure.getInterestRate().getName() : null)
            cs.setString(20, exposure.isGrouped() ? "Y" : "N")
            setLongWithNullCheck(cs, 21, exposure.getGroupedExposureId())
            setLongWithNullCheck(cs, 22, exposure.getGroupedClientId())
            cs.setBigDecimal(23, exposure.getBalanceHrk())
            cs.setBigDecimal(24, exposure.getSecuredPreviousHrk())
            cs.setBigDecimal(25, exposure.getSecuredProposedHrk())
            cs.setBigDecimal(26, exposure.getSecuredBalanceHrk())
            cs.setString(27, exposure.isLessThanYear() ? "Y" : "N")
            cs.setBigDecimal(28, exposure.getProposedHrk())
            cs.setInt(29, exposure.getTip())
            cs.setBigDecimal(30, exposure.getPreviousEur())
            cs.setBigDecimal(31, exposure.getChangeEur())
            cs.setBigDecimal(32, exposure.getBalanceEur())
            cs.setBigDecimal(33, exposure.getSecuredPreviousEur())
            cs.setBigDecimal(34, exposure.getSecuredProposedEur())
            cs.setBigDecimal(35, exposure.getSecuredBalanceEur())
            cs.setBigDecimal(36, exposure.getProposedEur())

            ResultSet rs = cs.executeQuery()
            if (rs.next()) {
                exposure.setId(rs.getLong(1))
            }
        } catch (Exception e) {
            log.info "Greska kod spremanja plasmana za klijenata sa IDjem $exposure.id $e.message"
            throw new RispoException("Greska kod spremanja plasmana za klijenata " + exposure.getClientId(), e)
        } finally {
            closeConnection(connection)
        }
        exposure
    }
}
