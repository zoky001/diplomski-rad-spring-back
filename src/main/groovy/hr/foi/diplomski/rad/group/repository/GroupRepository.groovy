package hr.foi.diplomski.rad.group.repository

import hr.foi.diplomski.rad.config.CallSoapService

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

import javax.sql.DataSource

import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.client.Client
import hr.foi.diplomski.rad.model.exposure.Exposure
import hr.foi.diplomski.rad.model.group.Group
import hr.foi.diplomski.rad.abstracts.AbstractRepository
import hr.foi.diplomski.rad.enums.ReportStatus

@TypeChecked
@Repository
@Slf4j
class GroupRepository extends AbstractRepository<Group> {

    @Autowired
    Sql sql

    DataSource dataSource

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    @Autowired
    GroupRepository(
    @Qualifier(value = 'datasource-db2') DataSource dataSource) {
	this.dataSource = dataSource
    }

    /**
     * 
     * @param id
     * @return
     * @throws RispoException
     */
	Group findOne(String id) throws RispoException {
		try {
			List<Group> groups = callSearchProcedure(1, id.toString(), 0)
			if (groups.isEmpty()) {
				return null
			}
			return groups.get(0)
		} catch (RispoException e) {
			log.info "Greska kod dohvata grupe sa ID-jem $id greska $e.message"
			throw new RispoException("Greska kod dohvata grupe sa ID-jem $id greska $e.message", e)
		}
	}

    /**
     * 
     * @param searchType
     * @param searchCriteria
     * @return
     * @throws RispoException
     */
	List<Group> findAll(int searchType, String searchCriteria) throws RispoException {
		try {
			List<Group> groups = callSearchAllProcedure(searchType, searchCriteria, 1)
			if (groups.isEmpty()) {
				return null
			}
			return groups
		} catch (RispoException e) {
			log.info "Greska kod dohvata svih grupa za kriterij: $searchCriteria greska $e.message"
			throw new RispoException("Greska kod dohvata svih grupa za kriterij: $searchCriteria greska $e.message", e)
		}
	}

    /**
     * 
     * @param searchType
     * @param searchQuery
     * @param status
     * @return
     * @throws RispoException
     */
	List<Group> find(int searchType, String searchQuery, int status) throws RispoException {
		List<Group> groups = null
		try {
			groups = callSearchProcedure(searchType, searchQuery, status)
		} catch (RispoException e) {
			log.info "Greska kod dohvata grupe za kriterij: $searchType $searchQuery $status greska $e.message"
			throw new RispoException("Greska kod dohvata grupe za kriterij: $searchType $searchQuery $status greska $e.message", e)
		}
		groups
	}

    /**
     * 
     * @param groupId
     * @return
     * @throws RispoException
     */
	List<String> findLogsByGroup(Long groupId) throws RispoException {
		List<String> logs = new ArrayList<String>()
		CallableStatement cs = null
		Connection connection = null
		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_LOGOVI(?)}")
			cs.setLong(1, groupId)
			ResultSet rs = cs.executeQuery()
			while (rs.next()) {
				logs.add(rs.getString(1))
			}
		} catch (Exception e) {
			log.info "Greska kod dohvacanja logova za grupu sa ID-jem $groupId greska $e.message"
			throw new RispoException("Greska kod dohvacanja logova za grupu sa ID-jem $groupId greska $e.message", e)
		} finally {
			closeConnection(connection)
		}
		return logs
	}

    /**
     * 
     * @param brRadnika
     * @param status
     * @return
     * @throws RispoException
     */
    List<Group> findByOwnerAndStatus(String brRadnika, ReportStatus status) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	List<Group> groups = new ArrayList<Group>()
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_STATUS(?,?)}")
	    cs.setInt(1, status.getValue())
	    cs.setString(2, brRadnika)
	    ResultSet rs = cs.executeQuery()
	    while (rs.next()) {
		Group g = mapLoadedGroup(rs)
		groups.add(g)
	    }
	} catch (Exception e) {
	    log.info "Greska kod dohvacanja izvjestaja za radnika $brRadnika greska $e.message"
	    throw new RispoException("Greska kod dohvacanja izvjestaja za radnika $brRadnika greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
	return groups
    }

    /**
     * 
     * @param kpo
     * @return
     * @throws RispoException
     */
    List<String> getDistinctMembersForGroup(String kpo) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	List<String> regNums = new ArrayList<String>()
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_DISTINCT_KLIJENTI(?)}")
	    cs.setString(1, kpo)
	    ResultSet rs = cs.executeQuery()
	    while (rs.next()) {
		regNums.add(rs.getString(1))
	    }
	} catch (Exception e) {
	    log.info "Greska kod dohvaćanja radnika za grupu $kpo greska $e.message"
	    throw new RispoException("Greska kod dohvaćanja radnika za grupu $kpo greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
	return regNums
    }

    /**
     * 
     * @param kpo
     * @param clanovi
     * @return
     * @throws RispoException
     */
    List<Group> findByKpoWithoutClients(String kpo, List<String> clanovi) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	List<Group> groups = new ArrayList<Group>()
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_BEZ_KLIJENATA(?,?)}")
	    cs.setString(1, kpo)
	    cs.setString(2, CallSoapService.collectionToString(clanovi))

	    ResultSet rs = cs.executeQuery()
	    while (rs.next()) {
		Group g = mapLoadedGroup(rs)
		groups.add(g)
	    }
	} catch (Exception e) {
	    log.info "Greska kod dohvaćanja izvjestaja za grupu $kpo greska $e.message"
	    throw new RispoException("Greska kod dohvaćanja izvjestaja za grupu $kpo greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
	return groups
    }


    boolean lock(Group group) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_UPDATE_STATUS(?,?)}")
	    cs.setLong(1, group.getId())
	    cs.setInt(2, ReportStatus.LOCKED.getValue())
	    cs.executeUpdate()
	} catch (Exception e) {
	    log.info "Greska kod zakljucavanja grupe sa IDjem $group.id greska $e.message"
	    throw new RispoException("Greska kod zakljucavanja grupe sa IDjem $group.id greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
	true
    }

    /**
     * 
     */
    Group delete(Group entity) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_DELETE(?)}")
	    cs.setLong(1, entity.getId())
	    cs.executeUpdate()
	} catch (Exception e) {
	    log.info "Greska kod brisanja grupe sa IDjem $entity.id greska $e.message"
	    throw new RispoException("Greska kod brisanja grupe sa IDjem $entity.id greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
	entity
    }

    /**
     * 
     */
	Group save(Group entity) throws RispoException {
		Group retObj
		if (entity.getId() == null) {
			retObj = callInsertProcedure(entity)
		} else {
			retObj = callUpdateProcedure(entity)
		}
		retObj
	}

    /**
     * 
     * @param g
     * @return
     * @throws RispoException
     */
	private Group callUpdateProcedure(Group g) throws RispoException {
		CallableStatement cs = null
		Connection connection = null
		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")
			cs.setLong(1, g.getId())
			cs.setString(2, g.getKpo())
			cs.setString(3, g.getName())
			cs.setString(4, g.getApplication())
			cs.setInt(5, g.getStatus().getValue())
			cs.setDate(6, new java.sql.Date(g.getReportDate().getMillis()))
			cs.setString(7, g.getOwner())
			cs.setString(8, g.getMb())
			cs.setString(9, g.getJmbg())
			cs.setString(10, g.getOib())
			cs.setNull(11, Types.VARCHAR)//OJ_GRUPA
			cs.setString(12, g.getCurrency())
			cs.setString(13, g.isDjelomicanDohvat() ? "Y" : "N")
			cs.setString(14, g.isDohvatPoPostojecimClanicama() ? "Y" : "N")
			cs.executeUpdate()
		} catch (Exception e) {
			log.info "Greska kod azuriranja grupe sa IDjem $g.id greska $e.message"
			throw new RispoException("Greska kod azuriranja grupe sa IDjem $g.id greska $e.message", e)
		} finally {
			closeConnection(connection)
		}
		g
	}

    /**
     * 
     * @param g
     * @return
     * @throws RispoException
     */
	private Group callInsertProcedure(Group g) throws RispoException {
		CallableStatement cs = null
		Connection connection = null
		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_INSERT(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")
			cs.setString(1, g.getKpo())
			cs.setString(2, g.getName())
			cs.setString(3, g.getApplication())
			cs.setInt(4, g.getStatus().getValue())
			cs.setDate(5, new java.sql.Date(g.getCreationDate().getMillis()))
			cs.setString(6, g.getOwner())
			cs.setInt(7, 0)
			cs.setString(8, g.getMb())
			cs.setString(9, g.getJmbg())
			cs.setString(10, g.getOib())
			cs.setNull(11, Types.VARCHAR)//OJ_GRUPA
			cs.setString(12, g.getCurrency())
			cs.setString(13, g.isDjelomicanDohvat() ? "Y" : "N")
			cs.setString(14, g.isDohvatPoPostojecimClanicama() ? "Y" : "N")
			ResultSet rs = cs.executeQuery()
			if (rs.next()) {
				g.setId(rs.getLong(1))
			}
		} catch (Exception e) {
			log.info "Greska kod spremanja grupe $g.name greska $e.message"
			throw new RispoException("Greska kod spremanja grupe $g.name greska $e.message", e)

		} finally {
			closeConnection(connection)
		}
		g
	}

    /**
     * 
     * @param searchType
     * @param searchValue
     * @param status
     * @return
     * @throws RispoException
     */
	private List<Group> callSearchProcedure(int searchType, String searchValue, int status) throws RispoException {
		CallableStatement cs = null
		Connection connection = null
		List<Group> groups = new ArrayList<Group>()

		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA(?,?,?)}")
			cs.setInt(1, searchType)
			cs.setString(2, searchValue)
			cs.setInt(3, status)
			ResultSet rs = cs.executeQuery()
			while (rs.next()) {
				Group group = mapLoadedGroup(rs)
				groups.add(group)
			}
		} catch (Exception e) {
			log.info "Greska kod dohvata izvjestaja za kriterij ( $searchType $searchValue $status ) greska $e.message"
			throw new RispoException("Greska kod dohvata izvjestaja za kriterij ( $searchType $searchValue $status ) greska $e.message", e)
		} finally {
			closeConnection(connection)
		}
		groups
	}

    /**
     * 
     * @param searchType
     * @param searchValue
     * @param status
     * @return
     * @throws RispoException
     */
	private List<Group> callSearchAllProcedure(int searchType, String searchValue, int status) throws RispoException {
		CallableStatement cs = null
		Connection connection = null
		List<Group> groups = new ArrayList<Group>()

		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_ALL(?,?,?)}")
			cs.setInt(1, searchType)
			cs.setString(2, searchValue)
			cs.setInt(3, status)
			ResultSet rs = cs.executeQuery()
			while (rs.next()) {
				Group group = mapLoadedGroup(rs)
				groups.add(group)
			}
		} catch (Exception e) {
			log.info "Greska kod dohvata izvjestaja za kriterij ( $searchType $searchValue $status ) greska $e.message"
			throw new RispoException("Greska kod dohvata izvjestaja za kriterij ( $searchType $searchValue $status ) greska $e.message", e)
		} finally {
			closeConnection(connection)
		}
		groups
	}

    /**
     * 
     * @param status
     * @param date
     * @return
     * @throws RispoException
     */
	List<Group> findByStatusAndDate(int status, DateTime date) throws RispoException {
		CallableStatement cs = null
		Connection connection = null
		List<Group> groups = new ArrayList<Group>()

		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_IN_PROGRESS(?,?)}")
			cs.setInt(1, status)
			cs.setDate(2, new java.sql.Date(date.getMillis()))
			ResultSet rs = cs.executeQuery()
			while (rs.next()) {
				Group group = mapLoadedGroup(rs)
				groups.add(group)
			}
		} catch (Exception e) {
			log.info "Greska kod dohvata izvjestaja za kriterij ( $status $date ) greska $e.message"
			throw new RispoException("Greska kod dohvata izvjestaja za kriterij ( $status $date ) greska $e.message", e)
		} finally {
			closeConnection(connection)
		}
		groups
	}

    /**
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    private Group mapLoadedGroup(ResultSet rs) throws SQLException {
	Long id = rs.getLong("ID_GRUPA")
	String kpo = rs.getString("KPO") == null ? null : rs.getString("KPO").trim()
	String name = rs.getString("NAZIV") == null ? null : rs.getString("NAZIV").trim()
	String application = rs.getString("APLIKACIJA")
	if (application != null) {
	    application = application.trim()
	}
	int status = rs.getInt("STATUS")
	float progress = rs.getFloat("PROGRESS")
	DateTime reportDate = new DateTime(rs.getDate("DATUM_IZRADE").getTime())
	DateTime creationDate = new DateTime(rs.getDate("TIMESTAMP_UNOS").getTime())
	String owner = rs.getString("RADNIK_UNOS")
	if (owner != null) {
	    owner = owner.trim()
	}
	String mb = rs.getString("MBG")
	String jmbg = rs.getString("JMBG")
	String oib = rs.getString("OIB")
	String currency = rs.getString("VALUTA")
	boolean djelomicanDohvat = "Y".equals(rs.getString("DJELOMICAN_DOHVAT")) ? true : false
	boolean dohvatPoPostojecimClanicama = "Y".equals(rs.getString("DOHVAT_PO_POSTOJECIM_CLANOVIMA")) ? true : false
	String orgJed = rs.getString("OJ_GRUPA")

	List<Client> members
	Exposure total

	Group group = new Group(id:id, name: name, kpo: kpo, mb: mb, jmbg: jmbg, oib: oib, application: application, 
		status: ReportStatus.valueOf(status), progress: progress, reportDate: reportDate, owner: owner, 
		members: members, total: total, creationDate: creationDate,
		currency: currency, djelomicanDohvat: djelomicanDohvat, dohvatPoPostojecimClanicama: dohvatPoPostojecimClanicama, 
		orgJed: orgJed, intRateHRK: new BigDecimal(0), intRateEUR: new BigDecimal(0), feesHRK: new BigDecimal(0), 
		feesEUR: new BigDecimal(0))
	group
    }

    /**
     * 
     * @param groupId
     * @param owner
     * @return
     * @throws RispoException
     */
    boolean updateOwner(Long groupId, String owner) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()
	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_GRUPA_UPDATE_KREATORA(?,?)}")
	    cs.setLong(1, groupId)
	    cs.setString(2, owner)
	    cs.executeUpdate()
	} catch (Exception e) {
	    log.info "Greska kod azuriranja vlasnika grupe $groupId greska $e.message"
	    throw new RispoException("Greska kod azuriranja vlasnika grupe $groupId greska $e.message" , e)
	} finally {
	    closeConnection(connection)
	}
	true
    }

    /**
     * 
     * @param status
     * @param date
     * @param organizationalUnits
     * @return
     * @throws RispoException
     */
    List<Group> findByStatusAndOrganizationalUnits(Integer status, String date, Set<String> organizationalUnits) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	List<Group> groups = new ArrayList<Group>()

	try {
	    connection = dataSource.getConnection()

	    cs = connection.prepareCall("{call RIS" + schemaName + ".MOJI_IZVJESTAJI(?,?,?)}")
	    cs.setInt(1, status)
	    cs.setString(2, formatInputDate(date))
	    cs.setString(3, CallSoapService.collectionToString(organizationalUnits))
	    ResultSet rs = cs.executeQuery()
	    while (rs.next()) {
		Group group = mapLoadedGroup(rs)
		groups.add(group)
	    }
	    return groups
	} catch (Exception e) {
	    log.info "Greska kod dohvata mojih izvjestaja za kriterij ( $status $organizationalUnits $date ) greska $e.message"
	    throw new RispoException("Greska kod dohvata mojih izvjestaja za kriterij ( $status $organizationalUnits $date ) greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
    }

    /**
     * 
     * @param date
     * @return
     */
    private String formatInputDate(String date) {
	return "\'" + date + "\'"
    }

    /**
     * 
     * @param id
     * @param organizationalUnits
     * @return
     * @throws RispoException
     */
    boolean groupContainsOrganizationalUnits(Long id, String organizationalUnits) throws RispoException {
	CallableStatement cs = null
	Connection connection = null
	try {
	    connection = dataSource.getConnection()

	    cs = connection.prepareCall("{call RIS" + schemaName + ".SP_PRAVA(?,?)}")
	    cs.setLong(1, id)
	    cs.setString(2, organizationalUnits)
	    ResultSet rs = cs.executeQuery()
	    if (rs.next()) {
		return true
	    }
	    return false
	} catch (Exception e) {
	    log.info "Greska kod dohvata prava za grupu ( $id $organizationalUnits ) greska $e.message"
	    throw new RispoException("Greska kod dohvata prava za grupu ( $id $organizationalUnits ) greska $e.message", e)
	} finally {
	    closeConnection(connection)
	}
    }

    @Override
    public List<Group> findByOwnerId(Long ownerId) throws RispoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List save(List<Group> entity) throws RispoException {
	// TODO Auto-generated method stub
	return null;
    }

//    private void closeConnection(Connection connection) {
//	try {
//	    if (connection != null) {
//		connection.close()
//	    }
//	} catch (SQLException e) {
//	    log.error("Greska kod zatvaranja konekcije", e)
//	}
//    }
}
