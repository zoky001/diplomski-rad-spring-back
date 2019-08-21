package hr.foi.diplomski.rad.interest.rate.reference.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.interest.rate.reference.service.InterestRateReferenceService
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/interestRateReference")
@TypeChecked
class InterestRateReferenceController {

    InterestRateReferenceService interestRateReferenceService
    DataConversionService dataConversionService

    @Autowired
    InterestRateReferenceController(InterestRateReferenceService interestRateReferenceService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.interestRateReferenceService=interestRateReferenceService
    }

    @GetMapping("/getReferenceByName")
    def findByOwnerId(@RequestParam String inRate) {
	ServiceResult sr = this.interestRateReferenceService.getReferenceByName(inRate)
	return dataConversionService.parseServiceResult(sr)
    }
    
    @GetMapping("/getEntries")
    def getEntries() {
	ServiceResult sr = this.interestRateReferenceService.getEntries()
	return dataConversionService.parseServiceResult(sr)
    }
    

}
