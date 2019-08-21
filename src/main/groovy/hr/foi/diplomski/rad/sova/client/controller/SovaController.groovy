package hr.foi.diplomski.rad.sova.client.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.service.DataConversionService
import hr.foi.diplomski.rad.sova.client.command.WSKorisnikAutorizacijaCommand
import hr.foi.diplomski.rad.sova.client.command.WSKorisnikPOSifraCommand
import hr.foi.diplomski.rad.sova.client.service.SovaService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sova")
@TypeChecked
class SovaController {

    SovaService sovaService
    DataConversionService dataConversionService

    @Autowired
    SovaController(SovaService sovaService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.sovaService = sovaService
    }

    @PostMapping("/wSKorisnikPOSifra")
    def wSKorisnikPOSifra(@RequestBody WSKorisnikPOSifraCommand command) {
        ServiceResult sr = sovaService.wSKorisnikPOSifra(command)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/wSKorisnikAutorizacija")
    def wSKorisnikAutorizacija(@RequestBody WSKorisnikAutorizacijaCommand command) {
        ServiceResult sr = sovaService.wSKorisnikAutorizacija(command)
        return dataConversionService.parseServiceResult(sr)
    }
}
