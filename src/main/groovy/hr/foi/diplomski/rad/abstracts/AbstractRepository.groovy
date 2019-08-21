package hr.foi.diplomski.rad.abstracts

import hr.foi.diplomski.rad.model.exposure.InterestRateReference

import java.sql.CallableStatement
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

import javax.sql.DataSource

//import groovy.util.logging.Log4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.core.model.CodebookEntry


//@Log4j
abstract class AbstractRepository<T> {

    protected DataSource dataSource

    AbstractRepository() {
    }

    AbstractRepository(DataSource dataSource) {
        this.dataSource = dataSource
    }

    abstract List<T> findByOwnerId(Long ownerId) throws RispoException

    abstract T delete(T entity) throws RispoException

    abstract T save(T entity) throws RispoException

    abstract List save(List<T> entity) throws RispoException

    protected String getGetTrimmedStringOrNull(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName)
        if (result != null) {
            result = result.trim()
        }
        result
    }

    protected String getGetTrimmedStringOrNull(Object columnName) throws SQLException {
        String result = (String) columnName
        if (result == null) {
            result = result.trim()
        }
        result
    }

    protected void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close()
            }
        } catch (SQLException e) {
            log.error("Greska kod zatvaranja konekcije $e.message", e)
        }
    }

    protected boolean getBoolean(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName)
        if (value == null) {
            return false
        } else if (value.trim().equals("Y")) {
            return true
        } else {
            return false
        }
    }

    protected void setStringWithNullCheck(CallableStatement cs, int columnIndex, String value) throws SQLException {
        if (value == null) {
            cs.setNull(columnIndex, Types.VARCHAR)
        } else {
            cs.setString(columnIndex, value)
        }
    }

    protected void setCodebookEntryWithNullCheck(CallableStatement cs, int columnIndex, CodebookEntry value) throws SQLException {
        if (value == null) {
            cs.setNull(columnIndex, Types.INTEGER)
        } else {
            cs.setInt(columnIndex, value.getId())
        }
    }

    protected void setInterestRateWithNullCheck(CallableStatement cs, int columnIndex, InterestRateReference value) throws SQLException {
        if (value == null) {
            cs.setNull(columnIndex, Types.INTEGER)
        } else {
            cs.setString(columnIndex, value.getName())
        }
    }

    protected void setDoubleWithNullCheck(CallableStatement cs, int columnIndex, Double value) throws SQLException {
        if (value == null) {
            cs.setNull(columnIndex, Types.DOUBLE)
        } else {
            cs.setDouble(columnIndex, value)
        }
    }

    protected void setBigDecimalWithNullCheck(CallableStatement cs, int columnIndex, BigDecimal value) throws SQLException {
        if (value == null) {
            cs.setNull(columnIndex, Types.DECIMAL)
        } else {
            cs.setBigDecimal(columnIndex, value)
        }
    }

    protected void setLongWithNullCheck(CallableStatement cs, int columnIndex, Long value) throws SQLException {
        if (value == null) {
            cs.setNull(columnIndex, Types.INTEGER)
        } else {
            cs.setLong(columnIndex, value)
        }
    }
}
