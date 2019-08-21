package hr.foi.diplomski.rad.zok.client.service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.config.CallSoapService
import hr.foi.diplomski.rad.generated.klijent.servis.client.model.PodaciOKlijentu
import hr.foi.diplomski.rad.generated.klijent.servis.client.model.PodaciOKlijentuResponse
import hr.foi.diplomski.rad.zok.client.command.PodaciOKlijentuCommand
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
@TypeChecked
class ZokService {

    @Value('${wsSettings.rispo.url.zok}')
    private String url_to_zok_service

    @Value('${wsSettings.zokService.user}')
    private String user_zok_service

    @Value('${wsSettings.zokService.pass}')
    private String pass_zok_service

    @Autowired
    CallSoapService callSoapService

    private static final String SCHEMA_ZOK_SERVICE = 'http://service.zok.zaba.hr/'

    String url

    ZokService() {
        callSoapService = new CallSoapService()
    }

    /**
     * podaciOKlijentu
     * @param command
     * @return
     */
    ServiceResult podaciOKlijentu(PodaciOKlijentuCommand command) {

        PodaciOKlijentu params = new PodaciOKlijentu(idKlijenta: command.brRegistra)

        log.info "ok - podaciOKlijentu " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResultBasicAuthorization(url_to_zok_service, SCHEMA_ZOK_SERVICE, params, PodaciOKlijentuResponse.class, user_zok_service, pass_zok_service)

        if (serviceResult.success) {
            log.info "ok - podaciOKlijentu"
            PodaciOKlijentuResponse response = (PodaciOKlijentuResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }

    /**
     * obradiOrgJedNaBrojZnamenaka
     * @param orgJed
     * @param brojZnamenaka
     * @return
     */
    String obradiOrgJedNaBrojZnamenaka(String orgJed, int brojZnamenaka) {
        if (orgJed == null || orgJed.isEmpty()) {
            return orgJed
        }
        return (orgJed + "00000000").substring(0, brojZnamenaka)
    }
}
