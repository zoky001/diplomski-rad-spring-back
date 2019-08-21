package hr.foi.diplomski.rad.core.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.core.command.*
import hr.foi.diplomski.rad.core.service.RispoService
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rispo")
@TypeChecked
class RispoController {

    RispoService rispoService
    DataConversionService dataConversionService

    @Autowired
    RispoController(RispoService rispoService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.rispoService = rispoService
    }

    @PostMapping("/azurirajIzlozenostClana")
    def azurirajIzlozenostClana(@RequestBody AzurirajIzlozenostClanaRispoServiceCommand command) {
        ServiceResult sr = rispoService.azurirajIzlozenostClana(
                command.id,
                command.registerNumber,
                command.userName)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/data")
    def getData(@RequestBody GetDataRispoServiceCommand command) {
        ServiceResult sr = rispoService.getData(
                command.criteria,
                command.criteriaType,
                command.oznDevize,
                command.datum,
                command.brRadnika,
                command.dohvatPoPostojecimClanicama)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/izlozenostSuma")
    def getIzlozenostSuma(@RequestParam int groupId,
                          @RequestParam String currency) {
        ServiceResult sr = rispoService.getIzlozenostSuma(
                groupId,
                currency)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/version")
    def getVersion() {
        ServiceResult sr = rispoService.getVersion()
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/ready")
    def isReady() {
        ServiceResult sr = rispoService.isReady()
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/vazeciRejting")
    def getVazeciRejting(@RequestBody GetVazeciRejtingServiceCommand command) {
        ServiceResult serviceResult = rispoService.getVazeciRejting(command)
        return dataConversionService.parseServiceResult(serviceResult)
    }

    @PostMapping("/tecaj")
    def getTecaj(@RequestBody GetTecajServiceCommand command) {
        ServiceResult serviceResult = rispoService.getTecaj(command)
        return dataConversionService.parseServiceResult(serviceResult)
    }

    @GetMapping("/groupsByRegNum")
    def getGroupsByRegNum(@RequestParam String regNum) {
        ServiceResult sr = rispoService.getGroupsByRegNum(regNum)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/groupData")
    def getGroupData(@RequestParam String kpo) {
        ServiceResult sr = rispoService.getGroupData(kpo)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/identification")
    def identification(@RequestBody IdentificationCommand command) {
        ServiceResult serviceResult = rispoService.identification(command)
        return dataConversionService.parseServiceResult(serviceResult)
    }
}
