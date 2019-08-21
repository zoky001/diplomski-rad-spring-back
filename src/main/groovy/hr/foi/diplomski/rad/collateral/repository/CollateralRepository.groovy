package hr.foi.diplomski.rad.collateral.repository

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.exposure.Collateral
import hr.foi.diplomski.rad.abstracts.AbstractRepository

@TypeChecked
@Repository
@Slf4j
class CollateralRepository extends AbstractRepository<Collateral> {

    @Autowired
    Sql sql

    DataSource dataSource

    @Autowired
    CollateralRepository (
    @Qualifier(value = 'datasource-db2') DataSource dataSource) {
	this.dataSource = dataSource
    }

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    /**
     * save
     */
    @Override
    List<Collateral> findByOwnerId(Long ownerId) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	List<Collateral> collaterals = new ArrayList<Collateral>()

	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KOLATERAL(?)}")
	    cs.setLong(1, ownerId)
	    ResultSet rs = cs.executeQuery()
	    while (rs.next()) {
		Long id = rs.getLong("ID_KOLATERAL")
		String name = getGetTrimmedStringOrNull(rs, "NAZIV")
		BigDecimal valueHrk = rs.getBigDecimal("VRIJEDNOST_HRK")
		BigDecimal valueEur = rs.getBigDecimal("VRIJEDNOST_EUR")
		Long eId = rs.getLong("ID_IZLOZENOST")
		collaterals.add(new Collateral(id: id, name: name, valueHrk: valueHrk, valueEur: valueEur, exposureId: eId))
	    }
	} catch (Exception e) {
	    log.info "Greska kod dohvata kolaterala za izloženost sa IDjem $ownerId $e.message" 
	    throw new RispoException("Greska kod dohvata kolaterala za izloženost sa IDjem " + ownerId,e)
	} finally {
	    closeConnection(connection)
	}
	collaterals
    }

    void deleteAll(Long ownerId) throws RispoException {
	List<Collateral> collaterals = findByOwnerId(ownerId)
	if (collaterals != null && !collaterals.isEmpty()) {
	    for (Collateral c : collaterals) {
		delete(c)
	    }
	}
    }

    /**
     * save
     */
    @Override
    Collateral delete(Collateral entity) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KOLATERAL_DELETE(?)}")
	    cs.setLong(1, entity.getId())
	    cs.executeUpdate()
	} catch (Exception e) {
	    log.info "Greska kod brisanja kolaterala sa IDjem $entity.id $e.message"
	    throw new RispoException("Greska kod brisanja kolaterala sa IDjem" + entity.getId(),e)
	} finally {
	    closeConnection(connection)
	}
	entity
    }

    /**
     * save
     */
    @Override
    Collateral save(Collateral entity) throws RispoException {
	Collateral retObj
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
    List<Collateral> save(List<Collateral> entities) throws RispoException {
	List<Collateral> retVal = new ArrayList<Collateral>()
	Collateral retObj
	boolean error = false
	for (Collateral entity : entities) {
	    try {
		retObj = save(entity)
		retVal.add(retObj)
	    } catch (RispoException e) {
		error = true
	    }
	    if(error){
		log.info "Greska kod spremanja kolaterala."
		throw new RispoException("Greska kod spremanja kolaterala.")
	    }
	}
	retVal
    }

    /**
     * callUpdateProcedure
     * @param c
     * @return
     * @throws RispoException
     */
    private Collateral callUpdateProcedure(Collateral c) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KOLATERAL_UPDATE(?,?,?,?,?)}")
	    cs.setLong(1, c.getId())
	    cs.setString(2, c.getName())
	    cs.setBigDecimal(3, c.getValueHrk())
	    cs.setLong(4, c.getExposureId())
	    cs.setBigDecimal(5, c.getValueEur())
	    cs.executeUpdate()
	} catch (Exception e) {
	    log.info "Greska kod azuriranja kolaterala sa IDjem $c.id $e.message"
	    throw new RispoException("Greska kod azuriranja kolaterala sa IDjem " + c.getId(),e)
	} finally {
	    closeConnection(connection)
	}
	c
    }

    /**
     * callInsertProcedure
     * @param c
     * @return
     * @throws RispoException
     */
    private Collateral callInsertProcedure(Collateral c) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_KOLATERAL_INSERT(?,?,?,?)}")
	    cs.setString(1, c.getName())
	    cs.setBigDecimal(2, c.getValueHrk())
	    cs.setLong(3, c.getExposureId())
	    cs.setBigDecimal(4, c.getValueEur())
	    ResultSet rs = cs.executeQuery()
	    if (rs.next()) {
		c.setId(rs.getLong(1))
	    }
	} catch (Exception e) {
	    log.info "Greska kod spremanja kolaterala za plasman sa IDjem $c.exposureId $e.message"
	    throw new RispoException("Greska kod spremanja kolaterala za plasman sa IDjem " + c.getExposureId(),e)
	} finally {
	    closeConnection(connection)
	}
	c
    }
}
