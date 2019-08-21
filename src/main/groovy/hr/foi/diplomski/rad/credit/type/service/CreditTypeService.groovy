package hr.foi.diplomski.rad.credit.type.service

import hr.zaba.emaframework.core.model.ServiceResult

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.exposure.TypeOfCreditEntry

@Slf4j
@Service
@TypeChecked
public class CreditTypeService {

    @Autowired
    Sql sql

    DataSource dataSource

    @Autowired
    CreditTypeService(
            @Qualifier(value = 'datasource-db2') DataSource dataSource) {
        this.dataSource = dataSource
    }

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    // private List<TypeOfCreditEntry> typeOfCreditConstants

    /**
     * loadTypeOfCreditData
     * @return
     */
    ServiceResult loadTypeOfCreditData() throws RispoException {

        CallableStatement cs = null
        Connection connection = null
        ServiceResult serviceResult

        List<TypeOfCreditEntry> typeOfCreditConstants = new ArrayList<TypeOfCreditEntry>()

        try {

            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_TYPE_OF_CREDIT()}")
            ResultSet rs = cs.executeQuery()
            while (rs.next()) {
                int id = rs.getInt("ID")
                String aplikacija = rs.getString("APLIKACIJA").trim()
                String vrstaOznakaPosla = rs.getString("VRSTA_OZNAKA_POSLA")
                String kategorija = rs.getString("KATEGORIJA")
                String sifraNamjene = rs.getString("SIFRA_NAMJENE")
                String oznakaVrstePlasmana = rs.getString("OZNAKA_VRSTE_PLASMANA")
                String nacinKoristenja = rs.getString("NACIN_KORISTENJA")
                Integer poredak = rs.getInt("POREDAK")
                typeOfCreditConstants.add(new TypeOfCreditEntry(id: id, aplikacija: aplikacija, vrstaOznakaPosla: vrstaOznakaPosla, kategorija: kategorija, sifraNamjene: sifraNamjene,
                        oznakaVrstePlasmana: oznakaVrstePlasmana, nacinKoristenja: nacinKoristenja, poredak: poredak))
            }
            serviceResult = new ServiceResult(success: true, result: typeOfCreditConstants)

        } catch (SQLException e) {
            log.error("Greska kod citanja TypeOfCredit šifrarnika")
            // throw new RispoException("Greska kod citanja TypeOfCredit šifrarnika", e)
            serviceResult = new ServiceResult(success: false, errorMessageTextList: ["Greska kod citanja sifrarnika code:13"])

        } finally {
            try {
                if (connection != null) {
                    connection.close()
                }
            } catch (SQLException e) {
                log.error("Greska kod zatvaranja konekcije", e)
                serviceResult = new ServiceResult(success: false, errorMessageTextList: ["Greska kod citanja sifrarnika code:13"])

            }
        }
        serviceResult
    }


    /**
     * update
     * @param entry
     * @return
     */
    boolean update(TypeOfCreditEntry entry) {
        executeProcedure(entry, 1)
        return true
    }

    /**
     * add
     * @param entry
     * @return
     */
    public boolean add(TypeOfCreditEntry entry) {
        executeProcedure(entry, 2)
        return true

    }

    /**
     * delete
     * @param entry
     * @return
     */
    public boolean delete(TypeOfCreditEntry entry) {
        executeProcedure(entry, 3)
        return true

    }

    /**
     * executeProcedure
     * @param entry
     * @param action
     */
    private void executeProcedure(TypeOfCreditEntry entry, int action) throws RispoException {
        CallableStatement cs = null
        Connection connection = null
        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + schemaName + ".SP_TYPE_OF_CREDIT_AZUR(?,?,?,?,?,?,?,?,?)}")
            cs.setInt(1, action)
            if (action != 2) {
                cs.setInt(2, entry.getId())
            } else {
                cs.setNull(2, java.sql.Types.INTEGER)
            }
            cs.setString(3, entry.getAplikacija())
            cs.setString(4, processString(entry.getVrstaOznakaPosla()))
            cs.setString(5, processString(entry.getKategorija()))
            cs.setString(6, processString(entry.getSifraNamjene()))
            cs.setString(7, processString(entry.getOznakaVrstePlasmana()))
            cs.setString(8, processString(entry.getNacinKoristenja()))
            cs.setInt(9, entry.getPoredak())
            cs.executeUpdate()
            loadTypeOfCreditData()
        } catch (SQLException e) {
            log.error("Greska kod poziva TYPE_OF_CREDIT_AZUR procedure {akcija: " + action + ", podaci: " + entry + "}")
            // todothrow new RispoException("Greska kod citanja TypeOfCredit šifrarnika", e)
        } finally {
            try {
                if (connection != null) {
                    connection.close()
                }
            } catch (SQLException e) {
                log.error("Greska kod zatvaranja konekcije", e)
            }
        }
    }

    /**
     * processString
     * @param value
     * @return
     */
    public String processString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null
        }
        return value.trim()
    }
}
