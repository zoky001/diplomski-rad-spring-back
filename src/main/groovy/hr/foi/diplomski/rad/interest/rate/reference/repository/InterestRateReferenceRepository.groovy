package hr.foi.diplomski.rad.interest.rate.reference.repository

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.exposure.InterestRateReference

@TypeChecked
@Repository
@Slf4j
class InterestRateReferenceRepository {

	@Autowired
	Sql sql

	DataSource dataSource

	Map<String, InterestRateReference> interestRateReferencesGlobal

	@Autowired
	InterestRateReferenceRepository (
	@Qualifier(value = 'datasource-db2') DataSource dataSource) {
		this.dataSource = dataSource
	}

	@Value('${wsSettings.rispoService.schemaName}')
	private String schemaName

	/**
	 * loadData
	 * @return
	 */
	Map<String, InterestRateReference> loadData() throws RispoException {
		CallableStatement cs = null
		Connection connection = null
		Map<String, InterestRateReference> interestRateReferences = new LinkedHashMap<String, InterestRateReference>()
		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call ZE" + schemaName + ".SP_NAZIV_STOPE_KRE()}")
			ResultSet rs = cs.executeQuery()
			while (rs.next()) {
				String name = rs.getString("OZNAKA")!=null?rs.getString("OZNAKA").trim():null
				String description = rs.getString("OPIS")!=null?rs.getString("OPIS"):null
				interestRateReferences.put(name, new InterestRateReference(name: name, description: description))
			}
		} catch (SQLException e) {
			log.info("Greska kod citanja InterestRateReference sifrarnika $e.message")
			throw new RispoException(e.message)
		} finally {
			try {
				if (connection != null) {
					connection.close()
				}
			} catch (SQLException e) {
				log.error("Greska kod zatvaranja konekcije $e.message", e)
			}
		}
		interestRateReferences
	}

	/**
	 * getReferenceByName
	 * @param name
	 * @return
	 */
	InterestRateReference getReferenceByName(String name) throws RispoException {
		InterestRateReference retVal = null
		Map<String, InterestRateReference> interestRateReferences = null
		try {
			interestRateReferences = loadData()
			if (interestRateReferences.containsKey(name)) {
				retVal = interestRateReferences.get(name)
			} else {
				log.error("Nema kamatne stope sa oznakom $name")
				retVal = null
			}
		} catch (RispoException e){
			throw e
		}
		retVal
	}

	/**
	 * getEntries
	 * @return
	 */
	Collection<InterestRateReference> getEntries()throws RispoException {
		Map<String, InterestRateReference> interestRateReferences
		try {
			interestRateReferences = loadData()
		} catch (RispoException e){
			throw e
		}

		return interestRateReferences.values()
	}
}
