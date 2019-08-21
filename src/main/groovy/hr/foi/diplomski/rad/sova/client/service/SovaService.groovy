package hr.foi.diplomski.rad.sova.client.service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.config.CallSoapService
import hr.foi.diplomski.rad.sova.client.command.WSKorisnikAutorizacijaCommand
import hr.foi.diplomski.rad.sova.client.command.WSKorisnikPOSifraCommand
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.generated.sova.client.model.model.WSKorisnikAutorizacija
import hr.foi.diplomski.rad.generated.sova.client.model.model.WSKorisnikAutorizacijaResponse
import hr.foi.diplomski.rad.generated.sova.client.model.model.WSKorisnikPOSifra
import hr.foi.diplomski.rad.generated.sova.client.model.model.WSKorisnikPOSifraResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
@TypeChecked
class SovaService {

    @Value('${wsSettings.rispo.url.sova}')
    private String url_to_sova_service

    @Autowired
    CallSoapService callSoapService

    private static final String SCHEMA_SOVA_SERVICE = 'http://controller.web.SOVAServices.zaba.hr/'

    String url

    SovaService() {
        callSoapService = new CallSoapService()
    }

    /**
     * wSKorisnikPOSifra
     * @param command
     * @return
     */
    ServiceResult wSKorisnikPOSifra(WSKorisnikPOSifraCommand command) {

        WSKorisnikPOSifra params = new WSKorisnikPOSifra(arg0: command.username, arg1: command.function, arg2: command.action)

        log.info "ok - wSKorisnikPOSifra " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_sova_service, SCHEMA_SOVA_SERVICE, params, WSKorisnikPOSifraResponse.class)

        if (serviceResult.success) {
            log.info "ok - wSKorisnikPOSifra"
            WSKorisnikPOSifraResponse response = (WSKorisnikPOSifraResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }


    /**
     * wSKorisnikAutorizacija
     * @param command
     * @return
     */
    ServiceResult wSKorisnikAutorizacija(WSKorisnikAutorizacijaCommand command) {

        WSKorisnikAutorizacija params = new WSKorisnikAutorizacija(arg0: command.app, arg1: command.username)

        log.info "ok - wSKorisnikAutorizacija " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_sova_service, SCHEMA_SOVA_SERVICE, params, WSKorisnikAutorizacijaResponse.class)

        if (serviceResult.success) {
            log.info "ok - wSKorisnikAutorizacija"
            WSKorisnikAutorizacijaResponse response = (WSKorisnikAutorizacijaResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }
}
