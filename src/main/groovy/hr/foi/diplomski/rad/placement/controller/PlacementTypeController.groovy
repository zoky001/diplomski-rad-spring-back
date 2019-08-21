package hr.foi.diplomski.rad.placement.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.placement.model.PlasmanTypeEntry
import hr.foi.diplomski.rad.placement.service.PlacementService
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/placementType")
@TypeChecked
class PlacementTypeController {

    PlacementService placementService
    DataConversionService dataConversionService


    @Autowired
    PlacementTypeController(PlacementService placementService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.placementService = placementService
    }


    @GetMapping("/loadEntries")
    def loadEntries() {
        ServiceResult sr = placementService.loadEntries()
        return dataConversionService.parseServiceResult(sr)
    }


    @PostMapping("/save")
    def save(@RequestBody PlasmanTypeEntry entry) {
        ServiceResult sr

        if (entry.id != null) {
            sr = placementService.update(entry)

        } else {
            sr = placementService.add(entry)
        }
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/delete")
    def delete(@RequestBody PlasmanTypeEntry entry) {
        ServiceResult sr = placementService.delete(entry)
        return dataConversionService.parseServiceResult(sr)
    }

}
