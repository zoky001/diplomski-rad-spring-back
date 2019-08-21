package hr.foi.diplomski.rad.zok.client.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.service.DataConversionService
import hr.foi.diplomski.rad.zok.client.command.PodaciOKlijentuCommand
import hr.foi.diplomski.rad.zok.client.service.ZokService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/zok")
@TypeChecked
class ZokController {

    ZokService zokService
    DataConversionService dataConversionService

    @Autowired
    zokController(ZokService zokService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.zokService = zokService
    }

    @PostMapping("/podaciOKlijentu")
    def podaciOKlijentu(@RequestBody PodaciOKlijentuCommand command) {
        ServiceResult sr = zokService.podaciOKlijentu(command)
        return dataConversionService.parseServiceResult(sr)
    }


}
