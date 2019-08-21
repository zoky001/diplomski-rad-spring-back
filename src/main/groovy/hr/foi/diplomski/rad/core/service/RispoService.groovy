package hr.foi.diplomski.rad.core.service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.config.CallRestService
import hr.foi.diplomski.rad.config.CallSoapService
import hr.foi.diplomski.rad.core.command.GetTecajServiceCommand
import hr.foi.diplomski.rad.core.command.GetVazeciRejtingServiceCommand
import hr.foi.diplomski.rad.core.command.IdentificationCommand
import hr.foi.diplomski.rad.generated.irws.client.model.GetVazeciRejtingResponse
import hr.foi.diplomski.rad.generated.povos.client.model.GetGroupData
import hr.foi.diplomski.rad.generated.povos.client.model.GetGroupDataResponse
import hr.foi.diplomski.rad.generated.povos.client.model.GetGroupsByRegNum
import hr.foi.diplomski.rad.generated.povos.client.model.GetGroupsByRegNumResponse
import hr.foi.diplomski.rad.generated.tecaj.deviza.client.model.GetTecaj
import hr.foi.diplomski.rad.generated.tecaj.deviza.client.model.GetTecajResponse
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.generated.rispo.ws.client.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
@TypeChecked
class RispoService {

    @Value('${wsSettings.rispo.url.rispoWS}')
    private String url_to_rispo_ws_service

    @Value('${wsSettings.rispo.url.irws}')
    private String url_to_irws_service

    @Value('${wsSettings.rispo.url.tecajDevize}')
    private String url_to_tecajDevize_service

    @Value('${wsSettings.rispo.url.povOsobe}')
    private String url_to_povOsobe_service

    @Value('${wsSettings.rispo.url.clientIdentification}')
    private String url_to_clientIdentification_service

    @Value('${wsSettings.zokService.user}')
    private String login

    @Value('${wsSettings.zokService.pass}')
    private String pass

    @Autowired
    CallSoapService callSoapService

    @Autowired
    CallRestService callRestService

    private static final String SCHEMA_RISPO_WS_SERVICE = 'http://main/'
    private static final String SCHEMA_IRWS_SERVICE = 'http://service.irws.zaba.hr/'
    private static final String SCHEMA_TECAJDEVIZE_SERVICE = 'http://service.tecaj.zaba.hr/'
    private static final String SCHEMA_POVOSOBE_SERVICE = 'http://service.ze.zaba.hr/';

    String url

    RispoService() {
        callSoapService = new CallSoapService()
        callRestService = new CallRestService()
    }

    /**
     * azurirajIzlozenostClana
     * @param id
     * @param registerNumber
     * @param userName
     * @return ServiceResult
     */
    ServiceResult azurirajIzlozenostClana(
            String id,
            String registerNumber,
            String userName) {

        AzurirajIzlozenostClana params = new AzurirajIzlozenostClana(
                arg0: id,
                arg1: registerNumber,
                arg2: userName)

        log.info "ok - azurirajIzlozenostClana " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_rispo_ws_service, SCHEMA_RISPO_WS_SERVICE, params, AzurirajIzlozenostClanaResponse.class)

        if (serviceResult.success) {
            log.info "ok - azurirajIzlozenostClana"
            AzurirajIzlozenostClanaResponse response = (AzurirajIzlozenostClanaResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }

    /**
     * getData
     * @param criteria
     * @param criteriaType
     * @param oznDevize
     * @param datum
     * @param brRadnika
     * @param dohvatPoPostojecimClanicama
     * @return
     */
    ServiceResult getData(
            String criteria,
            int criteriaType,
            String oznDevize,
            String datum,
            String brRadnika,
            boolean dohvatPoPostojecimClanicama) {

        GetData params = new GetData(
                criteria: criteria,
                criteriaType: criteriaType,
                oznDevize: oznDevize,
                datum: datum,
                brRadnika: brRadnika,
                dohvatPoPostojecimClanicama: dohvatPoPostojecimClanicama)

        params.setDohvatPoPostojecimClanicama(dohvatPoPostojecimClanicama)

        log.info "ok - getData " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_rispo_ws_service, SCHEMA_RISPO_WS_SERVICE, params, GetDataResponse.class)

        if (serviceResult.success) {
            log.info "ok - getData"
            GetDataResponse response = (GetDataResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }

    /**
     * getIzlozenostSuma
     * @param groupId
     * @param currency
     * @return ServiceResult
     */
    ServiceResult getIzlozenostSuma(
            int groupId,
            String currency) {

        GetIzlozenostSuma params = new GetIzlozenostSuma(
                arg0: groupId,
                arg1: currency)

        log.info "ok - getIzlozenostSuma " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_rispo_ws_service, SCHEMA_RISPO_WS_SERVICE, params, GetIzlozenostSumaResponse.class)

        if (serviceResult.success) {
            log.info "ok - getIzlozenostSuma"
            GetIzlozenostSumaResponse response = (GetIzlozenostSumaResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }

    /**
     * getVrsion
     * @return ServiceResult
     */
    ServiceResult getVersion() {

        GetVersion params = new GetVersion()

        log.info "ok - getVersion " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_rispo_ws_service, SCHEMA_RISPO_WS_SERVICE, params, GetVersionResponse.class)

        if (serviceResult.success) {
            log.info "ok - getVersion"
            GetVersionResponse response = (GetVersionResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn())
        }
        serviceResult
    }


    /**
     * isReady
     * @return ServiceResult
     */
    ServiceResult isReady() {

        IsReady params = new IsReady()

        log.info "ok - isReady " + params.dump()

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_rispo_ws_service, SCHEMA_RISPO_WS_SERVICE, params, IsReadyResponse.class)

        if (serviceResult.success) {
            log.info "ok - isReady"
            IsReadyResponse response = (IsReadyResponse) serviceResult.getResult()
            serviceResult.setResult(response.isReturn())
        }
        serviceResult
    }

    /**
     * getVazeciRejting
     * @param command
     * @return
     */
    ServiceResult getVazeciRejting(GetVazeciRejtingServiceCommand command) {

        log.info "ok - getVazeciRejting " + command.dump()

        GetVazeciRejtingServiceCommand params = new GetVazeciRejtingServiceCommand()
        params.brRegistra = command.brRegistra
        params.kpo = command.kpo
        params.datum = command.datum

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_irws_service, SCHEMA_IRWS_SERVICE, params, GetVazeciRejtingResponse.class);

        if (serviceResult.success) {
            GetVazeciRejtingResponse response = (GetVazeciRejtingResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn());
        }

        log.debug "ok - getVazeciRejting"

        serviceResult
    }

    /**
     * getTecaj
     * @param command
     * @return
     */
    ServiceResult getTecaj(GetTecajServiceCommand command) {

        log.info "ok - getTecaj " + command.dump()

        GetTecaj params = new GetTecaj(oznBurze: command.oznBurze, oznDevize: command.oznDevize, datum: command.datum, vrstaKlijenta: command.vrstaKlijenta, jezik: command.jezik)

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_tecajDevize_service, SCHEMA_TECAJDEVIZE_SERVICE, params, GetTecajResponse.class);

        if (serviceResult.success) {
            GetTecajResponse response = (GetTecajResponse) serviceResult.getResult()
            serviceResult.setResult(response.getReturn());
        }

        log.debug "ok - getTecaj"

        serviceResult
    }

    /**
     * getGroupsByRegNum
     * @param regNum
     * @return
     */
    ServiceResult getGroupsByRegNum(String regNum) {

        log.info "ok - getGroupsByRegNum $regNum"

        GetGroupsByRegNum params = new GetGroupsByRegNum(regNum: regNum);

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_povOsobe_service, SCHEMA_POVOSOBE_SERVICE, params, GetGroupsByRegNumResponse.class)

        log.debug "ok - getGroupsByRegNum"

        serviceResult
    }

    /**
     * getGroupData
     * @param kpo
     * @return
     */
    ServiceResult getGroupData(String kpo) {

        log.info "ok - getGroupData $kpo"

        GetGroupData params = new GetGroupData(kpo: kpo);

        ServiceResult serviceResult = (ServiceResult) callSoapService.callSoapServiceResult(url_to_povOsobe_service, SCHEMA_POVOSOBE_SERVICE, params, GetGroupDataResponse.class)

        log.debug "ok - getGroupData"

        serviceResult
    }

    /**
     * identification
     * @param command
     * @return
     */
    ServiceResult identification(IdentificationCommand command) {

        log.info "ok - identification $command"

        ServiceResult serviceResult = new ServiceResult()
        String url = url_to_clientIdentification_service + "/ci/identification/identify/$command.data/$command.type"
        Map<String, String> query = null
        def cookies = null

        serviceResult = callRestService.callRestGETWithBasicAuthorization(url, query, cookies, this.login, this.pass, "25000")

        log.info "ok - identification"

        serviceResult
    }
}
