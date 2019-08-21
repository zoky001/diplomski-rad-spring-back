package hr.foi.diplomski.rad.exposure.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.exposure.service.ExposureService
import hr.foi.diplomski.rad.model.exposure.Exposure
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exposure")
@TypeChecked
class ExposureController {

    ExposureService exposureService
    DataConversionService dataConversionService

    @Autowired
    ExposureController(ExposureService exposureService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.exposureService = exposureService
    }

    @GetMapping("/findByOwnerId")
    def findByOwnerId(@RequestParam Long ownerId) {
        ServiceResult sr = exposureService.findByOwnerId(ownerId)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/findByGroupedOwnerId")
    def findByGroupedOwnerId(@RequestParam Long ownerId) {
        ServiceResult sr = exposureService.findByGroupedOwnerId(ownerId)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/delete")
    def delete(@RequestBody Exposure exposure) {
        ServiceResult sr = exposureService.delete(exposure)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/save")
    def save(@RequestBody Exposure exposure) {
        ServiceResult sr = exposureService.save(exposure)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/saveList")
    def save(@RequestBody List<Exposure> entities) {
        ServiceResult sr = exposureService.save(entities)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/unGroup")
    def ungGroup(@RequestBody hr.foi.diplomski.rad.exposure.command.UnGroupCommand command) {
        ServiceResult sr = exposureService.unGroup(command.clientId)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/unGroupExposure")
    def ungGroupExposure(@RequestBody hr.foi.diplomski.rad.exposure.command.UnGroupCommand command) {
        ServiceResult sr = exposureService.unGroup(command.exposure, command.clientId)
        return dataConversionService.parseServiceResult(sr)
    }


}
