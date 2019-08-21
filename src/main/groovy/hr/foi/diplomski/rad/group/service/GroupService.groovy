package hr.foi.diplomski.rad.group.service

import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.group.Group
import hr.foi.diplomski.rad.enums.ReportStatus
import hr.foi.diplomski.rad.group.repository.GroupRepository

@Slf4j
@Service
@TypeChecked
class GroupService {

    @Autowired
    GroupRepository groupRepository

    GroupService(GroupRepository groupRepository) {
        super()
        this.groupRepository = groupRepository
    }

    ServiceResult findOne(String id) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findOne $id"
        try {
            repoResult = this.groupRepository.findOne(id)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findOne $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findOne"
        serviceResult
    }

    ServiceResult findAll(int searchType, String searchCriteria) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findAll $searchType $searchCriteria"
        try {
            repoResult = this.groupRepository.findAll(searchType, searchCriteria)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findAll $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findAll"
        serviceResult
    }

    ServiceResult find(int searchType, String searchQuery, int status) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - find $searchType $searchQuery $status"
        try {
            repoResult = this.groupRepository.find(searchType, searchQuery, status)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - find $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - find"
        serviceResult
    }

    ServiceResult findLogsByGroup(Long groupId) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findLogsByGroup $groupId"
        try {
            repoResult = this.groupRepository.findLogsByGroup(groupId)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findLogsByGroup $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findLogsByGroup"
        serviceResult
    }

    ServiceResult findByOwnerAndStatus(String brRadnika, ReportStatus status) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findByOwnerAndStatus $brRadnika $status"
        try {
            repoResult = this.groupRepository.findByOwnerAndStatus(brRadnika, status)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findByOwnerAndStatus $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findByOwnerAndStatus"
        serviceResult
    }

    ServiceResult getDistinctMembersForGroup(String kpo) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - getDistinctMembersForGroup $kpo"
        try {
            repoResult = this.groupRepository.getDistinctMembersForGroup(kpo)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - getDistinctMembersForGroup $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - getDistinctMembersForGroup"
        serviceResult
    }

    ServiceResult findByKpoWithoutClients(String kpo, List<String> clanovi) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findByKpoWithoutClients $kpo $clanovi"
        try {
            repoResult = this.groupRepository.findByKpoWithoutClients(kpo, clanovi)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findByKpoWithoutClients $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findByKpoWithoutClients"
        serviceResult
    }

    ServiceResult lock(Group group) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - lock $group"
        try {
            repoResult = this.groupRepository.lock(group)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - lock $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - lock"
        serviceResult
    }

    ServiceResult delete(Group entity) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - delete $entity"
        try {
            repoResult = this.groupRepository.delete(entity)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - delete $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - delete"
        serviceResult
    }

    ServiceResult save(Group entity) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - save $entity"
        try {
            repoResult = this.groupRepository.save(entity)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - save $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - save"
        serviceResult
    }

    ServiceResult findByStatusAndDate(int status, DateTime date) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findByStatusAndDate $status $date"
        try {
            repoResult = this.groupRepository.findByStatusAndDate(status, date)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findByStatusAndDate $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findByStatusAndDate"
        serviceResult
    }

    ServiceResult updateOwner(Long groupId, String owner) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - updateOwner $groupId $owner"
        try {
            repoResult = this.groupRepository.updateOwner(groupId, owner)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - updateOwner $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - updateOwner"
        serviceResult
    }

    ServiceResult findByStatusAndOrganizationalUnits(Integer status, String date, Set<String> organizationalUnits) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findByStatusAndOrganizationalUnits $status $date $organizationalUnits"
        try {
            repoResult = this.groupRepository.findByStatusAndOrganizationalUnits(status, date, organizationalUnits)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findByStatusAndOrganizationalUnits $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findByStatusAndOrganizationalUnits"
        serviceResult
    }

    ServiceResult groupContainsOrganizationalUnits(Long id, String organizationalUnits) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - groupContainsOrganizationalUnits $id $organizationalUnits"
        try {
            repoResult = this.groupRepository.groupContainsOrganizationalUnits(id, organizationalUnits)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - groupContainsOrganizationalUnits $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - groupContainsOrganizationalUnits"
        serviceResult
    }
}
