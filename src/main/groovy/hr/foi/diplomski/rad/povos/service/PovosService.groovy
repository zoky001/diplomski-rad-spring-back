package hr.foi.diplomski.rad.povos.service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.client.command.DohvatiGrupuPrimCommand
import hr.foi.diplomski.rad.config.CallSoapService
import hr.foi.diplomski.rad.generated.povos.client.model.DohvatiGrupuPrim
import hr.foi.diplomski.rad.generated.povos.client.model.DohvatiGrupuPrimResponse
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
@TypeChecked
class PovosService {

    @Value('${wsSettings.rispo.url.povOsobe}')
    private String url_to_povos_service

    @Autowired
    CallSoapService callSoapService

    private static final String SCHEMA_POVOS_SERVICE = 'http://service.ze.zaba.hr/'

    String url

    PovosService() {
        callSoapService = new CallSoapService()
    }

    /**
     * dohvatiGrupuPrim
     * @param command
     * @return
     */
    ServiceResult dohvatiGrupuPrim(DohvatiGrupuPrimCommand command) {

        DohvatiGrupuPrim params = new DohvatiGrupuPrim(identifikator: command.kpo, grupaFlg: command.flag)

        log.info "ok - dohvatiGrupuPrim " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_povos_service, SCHEMA_POVOS_SERVICE, params, DohvatiGrupuPrimResponse.class)

        if (serviceResult.success) {
            log.info "ok - dohvatiGrupuPrim"
            DohvatiGrupuPrimResponse response = (DohvatiGrupuPrimResponse) serviceResult.getResult()
            serviceResult.setResult(response.grupaPovezanihOsobaPrimarniClanovi)
        }
        serviceResult
    }

}
