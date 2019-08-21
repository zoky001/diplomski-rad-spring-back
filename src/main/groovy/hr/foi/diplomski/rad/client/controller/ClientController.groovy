package hr.foi.diplomski.rad.client.controller

import hr.foi.diplomski.rad.client.service.ClientService
import hr.foi.diplomski.rad.model.client.Client
import hr.foi.diplomski.rad.service.DataConversionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import groovy.transform.TypeChecked
import hr.zaba.emaframework.core.model.ServiceResult


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
@TypeChecked
class ClientController {

    ClientService clientService
    DataConversionService dataConversionService

    @Autowired
    ClientController(ClientService clientService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.clientService = clientService
    }

    @GetMapping("/findByOwnerId")
    def findByOwnerId(@RequestParam Long ownerId) {
        ServiceResult sr = clientService.findByOwnerId(ownerId)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/dohvatiVrstaOsobe")
    def dohvatiVrstaOsobe(@RequestParam String brRegistra) {
        ServiceResult sr = clientService.dohvatiVrstaOsobe(brRegistra)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/delete")
    def delete(@RequestBody Client client) {
        ServiceResult sr = clientService.delete(client)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/save")
    def save(@RequestBody Client client) {
        ServiceResult sr = clientService.save(client)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/saveList")
    def save(@RequestBody List<Client> entities) {
        ServiceResult sr = clientService.save(entities)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/getClientsForGroup")
    def getClientsForGroup(@RequestParam Long ownerId) {
        ServiceResult sr = clientService.getClientsForGroup(ownerId)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/unGroup")
    def ungGroup(@RequestBody Client client) {
        ServiceResult sr = clientService.unGroup(client)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/setPrimaryMember")
    def setPrimaryMember(@RequestBody Client client) {
        ServiceResult sr = clientService.setPrimaryMember(client)
        return dataConversionService.parseServiceResult(sr)
    }
}
