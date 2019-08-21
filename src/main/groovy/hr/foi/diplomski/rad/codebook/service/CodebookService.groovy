package hr.foi.diplomski.rad.codebook.service

import hr.foi.diplomski.rad.core.model.CodebookEntry
import hr.foi.diplomski.rad.enums.Codebooks
import hr.foi.diplomski.rad.model.RispoException
import hr.zaba.emaframework.core.model.ServiceResult

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types
import java.text.SimpleDateFormat

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

import groovy.sql.Sql
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j


@Service
@Slf4j
@TypeChecked
@Repository
class CodebookService {

    @Autowired
    Sql sql

    DataSource dataSource

    List<CodebookEntry> codebooksGlobal = new ArrayList<CodebookEntry>()
    Date lastCodebookDateGlobal = null

    @Autowired
    CodebookService(@Qualifier("datasource-db2") DataSource dataSource) {
        super()
        this.dataSource = dataSource
    }

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    /**
     * loadEntries
     * @return
     */
    ServiceResult loadEntries() {
        CallableStatement cs = null
        Connection connection = null
        ServiceResult serviceResult
        List<CodebookEntry> codebooks = new ArrayList<CodebookEntry>()

        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + this.schemaName + ".SP_SIFARNIK()}")
            ResultSet rs = cs.executeQuery()
            while (rs.next()) {
                int id = rs.getInt("ID")
                String nameEn = rs.getString("NAZIV_ENG").trim()
                Codebooks codebookType = Codebooks.valueOf(rs.getString("VRSTA").trim())
                codebooks.add(new CodebookEntry(id: id, name: nameEn, type: codebookType))
            }
            this.codebooksGlobal = codebooks
            this.lastCodebookDateGlobal = new Date()
            serviceResult = new ServiceResult(success: true, result: codebooks)
        } catch (Exception e) {
            log.error("Greska kod citanja CodebookService.loadEntries sifrarnika $e.message")
            serviceResult = new ServiceResult(success: false, errorMessageTextList: ["Greska kod citanja sifrarnika code:13"])
        } finally {
            try {
                if (connection != null) {
                    connection.close()
                }
            } catch (Exception e) {
                log.error("Greska kod zatvaranja konekcije", e)
                serviceResult = new ServiceResult(success: false, errorMessageTextList: ["Greska kod zatvaranja konekcije"])
            }
        }
        return serviceResult
    }


    ServiceResult update(CodebookEntry entry) {
        callProcedure(entry, 1)
    }

    ServiceResult add(CodebookEntry entry) {
        callProcedure(entry, 2)
    }

    ServiceResult delete(CodebookEntry entry) {
        callProcedure(entry, 3)
    }

    /**
     * callProcedure
     * @param entry
     * @param action
     * @return
     */
    private ServiceResult callProcedure(CodebookEntry entry, int action) {
        CallableStatement cs = null
        Connection connection = null
        ServiceResult serviceResult

        try {
            connection = dataSource.getConnection()
            cs = connection.prepareCall("{call RIS" + this.schemaName + ".SP_SIFARNIK_AZUR(?,?,?,?,?)}")
            cs.setInt(1, action)
            if (action != 2) {
                cs.setInt(2, entry.getId())
            } else {
                cs.setNull(2, java.sql.Types.INTEGER)
            }
            cs.setNull(4, Types.VARCHAR)
            cs.setString(3, entry.getType().toString())
            cs.setString(5, processString(entry.getName()))
            cs.executeUpdate()

            serviceResult = new ServiceResult(success: true)
        } catch (SQLException e) {
            log.error("Greska kod poziva SIFARNIK_AZUR procedure {akcija: " + action + ", podaci: " + entry + "}")
            serviceResult = new ServiceResult(success: false, errorMessageTextList: ["Greska kod poziva sifarnika code:11"])
        } finally {
            try {
                if (connection != null) {
                    connection.close()
                }
            } catch (SQLException e) {
                log.error("Greska kod zatvaranja konekcije", e)
                serviceResult = new ServiceResult(success: false, errorMessageTextList: ["Greska kod zatvaranja konekcije code: 12"])
            }
        }
        return serviceResult
    }

    /**
     * getCodebookEntry
     * @param id
     * @return
     */
    CodebookEntry getCodebookEntry(Integer id) throws RispoException {

        CodebookEntry retVal
        ServiceResult retService
        List<CodebookEntry> codebooks
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {

            if (this.codebooksGlobal == null || this.codebooksGlobal.isEmpty() || !sdf.format(this.lastCodebookDateGlobal).equals(sdf.format(new Date()))) {
                log.info("getCodebookEntry osvjezavam codebook id: $id last codebook day $lastCodebookDateGlobal")
                retService = loadEntries()
                codebooks = (List<CodebookEntry>) retService.result
            } else {
                codebooks = this.codebooksGlobal
            }

            if (codebooks != null && !codebooks.empty) {
                retVal = this.codebookListContainsId(id, codebooks)
            } else {
                log.info "getCodebookEntry Codebooks lista je prazna dohvaca se id: $id"
            }

            if (retVal == null) {
                log.info "getCodebookEntry Ne postoji sifrarnicki unos sa ID-jem " + id
            }
        } catch (Exception e) {
            log.info "getCodebookEntry id $id poruka: $e.message"
        }

        retVal
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

    /**
     * codebookListContainsId
     * @param id
     * @return
     */
    CodebookEntry codebookListContainsId(Integer id, List<CodebookEntry> codebooks) {
        CodebookEntry retVal = null
        CodebookEntry entry
        if (codebooks != null && !codebooks.empty) {
            for (Iterator iterator = codebooks.iterator(); iterator.hasNext();) {
                entry = (CodebookEntry) iterator.next();
                if (entry != null) {
                    if (entry.id == id) {
                        retVal = entry
                        break
                    }
                } else {
                    log.info("codebookListContainsId entry je null dohvaca se id: $id")
                }
            }
        } else {
            log.info("codebookListContainsId Codebooks lista je prazna dohvaca se id: $id")
        }

        retVal
    }
}
