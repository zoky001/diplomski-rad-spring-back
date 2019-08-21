package hr.foi.diplomski.rad.group.controller


import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.group.command.FindAllGroupCommand
import hr.foi.diplomski.rad.group.command.FindByKpoWithoutClientsGroupCommand
import hr.foi.diplomski.rad.group.command.FindByOwnerAndStatusGroupCommand
import hr.foi.diplomski.rad.group.command.FindByStatusAndDateGroupCommand
import hr.foi.diplomski.rad.group.service.GroupService
import hr.foi.diplomski.rad.model.group.Group
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/group")
@TypeChecked
@Slf4j
class GroupController {

    GroupService groupService
    DataConversionService dataConversionService

    @Autowired
    GroupController(GroupService groupService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.groupService = groupService
    }

    @GetMapping("/findOne")
    def findOne(@RequestParam String id) {
        ServiceResult sr = this.groupService.findOne(id)

        return dataConversionService.parseServiceResult(sr)
    }



    @PostMapping("/findAll")
    def findAll(@RequestBody FindAllGroupCommand command) {
        ServiceResult sr = this.groupService.findAll(command.searchType, command.searchCriteria)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/find")
    def find(@RequestBody hr.foi.diplomski.rad.group.command.FindGroupCommand command) {
        ServiceResult sr = this.groupService.find(command.searchType, command.searchQuery, command.status)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/findLogsByGroup")
    def findLogsByGroup(@RequestParam Long groupId) {
        ServiceResult sr = this.groupService.findLogsByGroup(groupId)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/findByOwnerAndStatus")
    def findByOwnerAndStatus(@RequestBody FindByOwnerAndStatusGroupCommand command) {
        ServiceResult sr = this.groupService.findByOwnerAndStatus(command.brRadnika, command.status)

        return dataConversionService.parseServiceResult(sr)
    }



    @GetMapping("/getDistinctMembersForGroup")
    def getDistinctMembersForGroup(@RequestParam String kpo) {
        ServiceResult sr = this.groupService.getDistinctMembersForGroup(kpo)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/findByKpoWithoutClients")
    def findByKpoWithoutClients(@RequestBody FindByKpoWithoutClientsGroupCommand command) {
        ServiceResult sr = this.groupService.findByKpoWithoutClients(command.kpo, command.clanovi)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/lock")
    def lock(@RequestBody Group group) {
        ServiceResult sr = this.groupService.lock(group)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/delete")
    def delete(@RequestBody Group group) {
        ServiceResult sr = this.groupService.delete(group)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/save")
    def save(@RequestBody Group group) {
        ServiceResult sr = this.groupService.save(group)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/findByStatusAndDate")
    def findByStatusAndDate(@RequestBody FindByStatusAndDateGroupCommand command) {
        ServiceResult sr = this.groupService.findByStatusAndDate(command.status, command.date)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/updateOwner")
    def updateOwner(@RequestParam Long groupId, @RequestParam String owner) {
        ServiceResult sr = this.groupService.updateOwner(groupId, owner)
        return dataConversionService.parseServiceResult(sr)
    }

    @PostMapping("/findByStatusAndOrganizationalUnits")
    def findByStatusAndOrganizationalUnits(@RequestBody hr.foi.diplomski.rad.group.command.FindByStatusAndOrganizationalUnitsGroupCommand command) {
        ServiceResult sr = this.groupService.findByStatusAndOrganizationalUnits(command.status, command.date, command.organizationalUnits)
        return dataConversionService.parseServiceResult(sr)
    }

    @GetMapping("/groupContainsOrganizationalUnits")
    def groupContainsOrganizationalUnits(@RequestParam Long id, @RequestParam String organizationalUnits) {
        ServiceResult sr = this.groupService.groupContainsOrganizationalUnits(id, organizationalUnits)
        return dataConversionService.parseServiceResult(sr)
    }
}
