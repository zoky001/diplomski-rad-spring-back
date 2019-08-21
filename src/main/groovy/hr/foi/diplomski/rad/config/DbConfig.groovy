package hr.foi.diplomski.rad.config

import org.springframework.boot.jdbc.DataSourceBuilder

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcCall
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup

import groovy.sql.Sql
import groovy.util.logging.Slf4j

@Configuration
@Slf4j
class DbConfig {

    @Value('${spring.datasource.jndi-name:}')
    String db2JndiName

    @Value('${wsSettings.rispoService.schemaName}')
    private String schemaName

    @Primary
    /**** @param jdbcTemplate * @return */ @Bean(name = 'datasource-db2')
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource db2Datasource() {
	log.info("Initializing DB2 datasource")

	def dataSource

	if (db2JndiName) {
	    log.info("Retrieving DB2 jndi datasource from jndi: ${db2JndiName}")
	    dataSource = new JndiDataSourceLookup().getDataSource(db2JndiName)
	} else {
	    log.info("Initializing db2 datasource from spring.datasource configuration.")
	    dataSource = DataSourceBuilder.create().build()
	}
	dataSource
    }
    /**
     * 
     * @param dataSource
     * @return
     */
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-sql')
    Sql db2Sql(@Qualifier(value = 'datasource-db2') DataSource dataSource) {
	new Sql(dataSource)
    }

    /**** @param jdbcTemplate * @return */
    @Bean(name = 'db2-groovy-ANONIMIZIRAJ_RISP_BY_JMBG_ProcedureCall') SimpleJdbcCall ANONIMIZIRAJ_RISP_BY_JMBG_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.ANONIMIZIRAJ_RISP_BY_JMBG")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('ANONIMIZIRAJ_RISP_BY_JMBG')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-ANONIMIZIRAJ_RISP_BY_REG_NO_ProcedureCall') SimpleJdbcCall ANONIMIZIRAJ_RISP_BY_REG_NO_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.ANONIMIZIRAJ_RISP_BY_REG_NO")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('ANONIMIZIRAJ_RISP_BY_REG_NO')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-MOJI_IZVJESTAJI_ProcedureCall') SimpleJdbcCall MOJI_IZVJESTAJI_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.MOJI_IZVJESTAJI")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('MOJI_IZVJESTAJI')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_DISTINCT_KLIJENTI_ProcedureCall') SimpleJdbcCall SP_DISTINCT_KLIJENTI_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_DISTINCT_KLIJENTI")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_DISTINCT_KLIJENTI')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_ProcedureCall') SimpleJdbcCall SP_GRUPA_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_ALL_ProcedureCall') SimpleJdbcCall SP_GRUPA_ALL_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_ALL")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_ALL')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_BEZ_KLIJENATA_ProcedureCall') SimpleJdbcCall SP_GRUPA_BEZ_KLIJENATA_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_BEZ_KLIJENATA")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_BEZ_KLIJENATA')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_DELETE_ProcedureCall') SimpleJdbcCall SP_GRUPA_DELETE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_DELETE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_DELETE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_IN_PROGRESS_ProcedureCall') SimpleJdbcCall SP_GRUPA_IN_PROGRESS_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_IN_PROGRESS")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_IN_PROGRESS')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_INSERT_ProcedureCall') SimpleJdbcCall SP_GRUPA_INSERT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_INSERT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_INSERT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_STATUS_ProcedureCall') SimpleJdbcCall SP_GRUPA_STATUS_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_STATUS")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_STATUS')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_UPDATE_ProcedureCall') SimpleJdbcCall SP_GRUPA_UPDATE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_UPDATE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_UPDATE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_UPDATE_KREATORA_ProcedureCall') SimpleJdbcCall SP_GRUPA_UPDATE_KREATORA_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_UPDATE_KREATORA")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_UPDATE_KREATORA')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_UPDATE_PROGRESS_ProcedureCall') SimpleJdbcCall SP_GRUPA_UPDATE_PROGRESS_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_UPDATE_PROGRESS")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_UPDATE_PROGRESS')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_GRUPA_UPDATE_STATUS_ProcedureCall') SimpleJdbcCall SP_GRUPA_UPDATE_STATUS_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_GRUPA_UPDATE_STATUS")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_GRUPA_UPDATE_STATUS')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_IZLOZENOST_ProcedureCall') SimpleJdbcCall SP_IZLOZENOST_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_IZLOZENOST")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_IZLOZENOST')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_IZLOZENOST_DELETE_ProcedureCall') SimpleJdbcCall SP_IZLOZENOST_DELETE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_IZLOZENOST_DELETE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_IZLOZENOST_DELETE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_IZLOZENOST_GRUPIRANO_ProcedureCall') SimpleJdbcCall SP_IZLOZENOST_GRUPIRANO_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_IZLOZENOST_GRUPIRANO")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_IZLOZENOST_GRUPIRANO')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_IZLOZENOST_INSERT_ProcedureCall') SimpleJdbcCall SP_IZLOZENOST_INSERT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_IZLOZENOST_INSERT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_IZLOZENOST_INSERT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_IZLOZENOST_UPDATE_ProcedureCall') SimpleJdbcCall SP_IZLOZENOST_UPDATE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_IZLOZENOST_UPDATE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_IZLOZENOST_UPDATE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KLIJENT_ProcedureCall') SimpleJdbcCall SP_KLIJENT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KLIJENT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KLIJENT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KLIJENT_DELETE_ProcedureCall') SimpleJdbcCall SP_KLIJENT_DELETE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KLIJENT_DELETE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KLIJENT_DELETE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KLIJENT_INSERT_ProcedureCall') SimpleJdbcCall SP_KLIJENT_INSERT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KLIJENT_INSERT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KLIJENT_INSERT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KLIJENT_PODACI_ProcedureCall') SimpleJdbcCall SP_KLIJENT_PODACI_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KLIJENT_PODACI")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KLIJENT_PODACI')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KLIJENT_UPDATE_ProcedureCall') SimpleJdbcCall SP_KLIJENT_UPDATE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KLIJENT_UPDATE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KLIJENT_UPDATE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KLIJENT_UPDATE_GRESKA_ProcedureCall') SimpleJdbcCall SP_KLIJENT_UPDATE_GRESKA_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KLIJENT_UPDATE_GRESKA")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KLIJENT_UPDATE_GRESKA')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KOLATERAL_ProcedureCall') SimpleJdbcCall SP_KOLATERAL_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KOLATERAL")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KOLATERAL')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KOLATERAL_DELETE_ProcedureCall') SimpleJdbcCall SP_KOLATERAL_DELETE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KOLATERAL_DELETE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KOLATERAL_DELETE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KOLATERAL_INSERT_ProcedureCall') SimpleJdbcCall SP_KOLATERAL_INSERT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KOLATERAL_INSERT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KOLATERAL_INSERT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_KOLATERAL_UPDATE_ProcedureCall') SimpleJdbcCall SP_KOLATERAL_UPDATE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_KOLATERAL_UPDATE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_KOLATERAL_UPDATE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_LOGOVI_ProcedureCall') SimpleJdbcCall SP_LOGOVI_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_LOGOVI")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_LOGOVI')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_LOGOVI_INSERT_ProcedureCall') SimpleJdbcCall SP_LOGOVI_INSERT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_LOGOVI_INSERT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_LOGOVI_INSERT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_PLASMAN_TYPES_ProcedureCall') SimpleJdbcCall SP_PLASMAN_TYPES_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_PLASMAN_TYPES")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_PLASMAN_TYPES')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_PLASMAN_TYPES_AZUR_ProcedureCall') SimpleJdbcCall SP_PLASMAN_TYPES_AZUR_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_PLASMAN_TYPES_AZUR")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_PLASMAN_TYPES_AZUR')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_POVIJESNI_DATUMI_ProcedureCall') SimpleJdbcCall SP_POVIJESNI_DATUMI_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_POVIJESNI_DATUMI")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_POVIJESNI_DATUMI')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_PRAVA_ProcedureCall') SimpleJdbcCall SP_PRAVA_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_PRAVA")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_PRAVA')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_PRAVA_NA_GRUPU_ProcedureCall') SimpleJdbcCall SP_PRAVA_NA_GRUPU_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_PRAVA_NA_GRUPU")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_PRAVA_NA_GRUPU')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_PRIM_CLAN_AZUR_ProcedureCall') SimpleJdbcCall SP_PRIM_CLAN_AZUR_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_PRIM_CLAN_AZUR")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_PRIM_CLAN_AZUR')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_SIFARNIK_ProcedureCall') SimpleJdbcCall SP_SIFARNIK_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_SIFARNIK")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_SIFARNIK')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_SIFARNIK_AZUR_ProcedureCall') SimpleJdbcCall SP_SIFARNIK_AZUR_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_SIFARNIK_AZUR")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_SIFARNIK_AZUR')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_SUMBYTYPE_ProcedureCall') SimpleJdbcCall SP_SUMBYTYPE_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_SUMBYTYPE")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_SUMBYTYPE')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_TYPE_OF_CREDIT_ProcedureCall') SimpleJdbcCall SP_TYPE_OF_CREDIT_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_TYPE_OF_CREDIT")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_TYPE_OF_CREDIT')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-SP_TYPE_OF_CREDIT_AZUR_ProcedureCall') SimpleJdbcCall SP_TYPE_OF_CREDIT_AZUR_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.SP_TYPE_OF_CREDIT_AZUR")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('SP_TYPE_OF_CREDIT_AZUR')
    }
    /**** @param jdbcTemplate * @return */ @Bean(name = 'db2-groovy-ZAMIJENI_JMBG_RISP_ProcedureCall') SimpleJdbcCall ZAMIJENI_JMBG_RISP_ProcedureCall(JdbcTemplate jdbcTemplate) {
	log.info("Configuring SimpleJdbcCall bean for calling RISTP.ZAMIJENI_JMBG_RISP")
	return new SimpleJdbcCall(jdbcTemplate).withSchemaName('RIS' + schemaName).withProcedureName('ZAMIJENI_JMBG_RISP')
    }
}
