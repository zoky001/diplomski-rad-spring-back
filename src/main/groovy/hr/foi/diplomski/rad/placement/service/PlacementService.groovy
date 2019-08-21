package hr.foi.diplomski.rad.placement.service

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.placement.model.PlasmanTypeEntry

@Service
@Slf4j
class PlacementService {

	@Value('${wsSettings.rispoService.schemaName}')
	private String schemaName

	private DataSource dataSource

	@Autowired
	PlacementService(@Qualifier("datasource-db2") DataSource dataSource) {
		this.dataSource = dataSource
	}

	/**
	 * update
	 * @param entry
	 * @return
	 */
	ServiceResult update(PlasmanTypeEntry entry) {
		executeProcedure(entry, 1)
	}

	/**
	 * add
	 * @param entry
	 * @return
	 */
	ServiceResult add(PlasmanTypeEntry entry) {
		executeProcedure(entry, 2)
	}

	/**
	 * delete
	 * @param entry
	 * @return
	 */
	ServiceResult delete(PlasmanTypeEntry entry) {
		executeProcedure(entry, 3)
	}

	/**
	 * executeProcedure
	 * @param entry
	 * @param action
	 * @return
	 */
	private ServiceResult executeProcedure(PlasmanTypeEntry entry, int action) {
		CallableStatement cs = null
		Connection connection = null
		ServiceResult serviceResult

		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_PLASMAN_TYPES_AZUR(?,?,?,?,?,?)}")
			cs.setInt(1, action)
			if (action != 2) {
				cs.setInt(2, entry.getId())
			} else {
				cs.setNull(2, java.sql.Types.INTEGER)
			}
			cs.setString(3, entry.getAplikacija())
			cs.setString(4, processString(entry.getOznakaLimita()))
			cs.setString(5, processString(entry.getSifraNamjene()))
			cs.setString(6, processString(entry.getTip()))
			cs.executeUpdate()
			serviceResult = new ServiceResult(success: true)
		} catch (SQLException e) {
			log.error("Greska kod poziva TYPE_OF_CREDIT_AZUR procedure {akcija: " + action + ", podaci: " + entry + "}")
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["Greska kod dohvata vrste plasmana"])
		} finally {
			try {
				if (connection != null) {
					connection.close()
				}
			} catch (SQLException e) {
				serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["Greska kod zatvaranja konekcije"])
			}
		}
		return serviceResult
	}

	/**
	 * loadEntries
	 * @return
	 */
	ServiceResult loadEntries() {
		CallableStatement cs = null
		Connection connection = null
		List<PlasmanTypeEntry> types = new ArrayList<PlasmanTypeEntry>()
		ServiceResult serviceResult

		try {
			connection = dataSource.getConnection()
			cs = connection.prepareCall("{call RIS" + schemaName + ".SP_PLASMAN_TYPES()}")
			ResultSet rs = cs.executeQuery()
			while (rs.next()) {
				Integer id = rs.getInt("ID")

				String aplikacija = rs.getString("APPLIKACIJA")
				if (aplikacija != null) {
					aplikacija = aplikacija.trim()
				}

				String oznLimita = rs.getString("OZN_LIMITA")
				if (oznLimita != null) {
					oznLimita = oznLimita.trim()
				}

				String sifNamjene = rs.getString("SIF_NAMJENE")
				if (sifNamjene != null) {
					sifNamjene = sifNamjene.trim()
				}

				String type = rs.getString("TYPE")
				if (type != null) {
					type = type.trim()
				}

				if (rs.getString("APPLIKACIJA") != null && rs.getString("TYPE") != null) {
					types.add(new PlasmanTypeEntry(id: id, aplikacija: aplikacija, oznakaLimita: oznLimita, sifraNamjene: sifNamjene, tip: type))
				}
			}

			serviceResult = new ServiceResult(success: true, result: types)
		} catch (Exception e) {
			log.error("Greska kod citanja PlasmanType sifrarnika", e)
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["Greska kod citanja sifrarnika code:13"])
		} finally {
			try {
				if (connection != null) {
					connection.close()
				}
			} catch (Exception e) {
				serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["Greska kod zatvaranja konekcije"])
			}
		}

		return serviceResult
	}

	/**
	 * processString
	 * @param value
	 * @return
	 */
	String processString(String value) {
		if (value == null || value.trim().isEmpty()) {
			return null
		}
		return value.trim()
	}
}
