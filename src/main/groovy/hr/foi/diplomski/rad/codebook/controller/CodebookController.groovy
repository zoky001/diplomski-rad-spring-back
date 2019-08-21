package hr.foi.diplomski.rad.codebook.controller

import hr.foi.diplomski.rad.codebook.service.CodebookService
import hr.foi.diplomski.rad.service.DataConversionService
import hr.foi.diplomski.rad.core.model.CodebookEntry
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import groovy.transform.TypeChecked


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/codebook")
@TypeChecked
class CodebookController {

    CodebookService codebookservice
    DataConversionService dataConversionService


    @Autowired
    CodebookController(CodebookService codebookservice, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.codebookservice = codebookservice
    }


    @GetMapping("/loadEntries")
    def loadEntries() {
        ServiceResult sr = codebookservice.loadEntries()
        return dataConversionService.parseServiceResult(sr)
    }


    @PostMapping("/save")
    def save(@RequestBody CodebookEntry entry) {
        ServiceResult sr

        if (entry.id != null) {
            sr = codebookservice.update(entry)

        } else {
            sr = codebookservice.add(entry)
        }
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/delete")
    def delete(@RequestBody CodebookEntry entry) {
        ServiceResult sr = codebookservice.delete(entry)
        return dataConversionService.parseServiceResult(sr)
    }

}
