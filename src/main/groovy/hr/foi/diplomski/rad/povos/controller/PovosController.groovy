package hr.foi.diplomski.rad.povos.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.client.command.DohvatiGrupuPrimCommand
import hr.foi.diplomski.rad.povos.service.PovosService
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/povos")
@TypeChecked
class PovosController {

    PovosService povosService
    DataConversionService dataConversionService

    @Autowired
    PovosController(PovosService povosService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.povosService = povosService
    }

    @PostMapping("/dohvatiGrupuPrim")
    def dohvatiGrupuPrim(@RequestBody DohvatiGrupuPrimCommand command) {
        ServiceResult sr = povosService.dohvatiGrupuPrim(command)
        return dataConversionService.parseServiceResult(sr)
    }


}
