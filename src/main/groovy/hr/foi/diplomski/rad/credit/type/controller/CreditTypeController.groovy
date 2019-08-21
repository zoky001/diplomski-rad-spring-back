package hr.foi.diplomski.rad.credit.type.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.credit.type.service.CreditTypeService
import hr.foi.diplomski.rad.model.exposure.TypeOfCreditEntry
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/creditType")
@TypeChecked
class CreditTypeController {

    CreditTypeService creditTypeService
    DataConversionService dataConversionService

    @Autowired
    CreditTypeController(CreditTypeService creditTypeService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.creditTypeService = creditTypeService
    }

    @GetMapping("/loadTypeOfCreditData")
    def loadTypeOfCreditData() {
        ServiceResult sr = creditTypeService.loadTypeOfCreditData()
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/save")
    def save(@RequestBody TypeOfCreditEntry entry) {
        ServiceResult sr = new ServiceResult();
        if (entry.id != null) {
            sr.result = creditTypeService.update(entry)
        } else {
            sr.result = creditTypeService.add(entry)
        }
        sr.success = true
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/delete")
    def delete(@RequestBody TypeOfCreditEntry entry) {
        ServiceResult sr = new ServiceResult();
        sr.result = creditTypeService.delete(entry)
        sr.success = true
        return dataConversionService.parseServiceResult(sr)
    }
}
