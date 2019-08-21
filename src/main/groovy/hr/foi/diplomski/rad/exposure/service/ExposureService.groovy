package hr.foi.diplomski.rad.exposure.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.exposure.Exposure
import hr.foi.diplomski.rad.exposure.repository.ExposureRepository


@Slf4j
@Service
@TypeChecked
class ExposureService {

    @Autowired
    ExposureRepository exposureRepository

    ExposureService(ExposureRepository exposureRepository) {
        super()
        this.exposureRepository = exposureRepository
    }

    /**
     * findByOwnerId
     * @param ownerId
     * @return
     */
    ServiceResult findByOwnerId(Long ownerId) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findByOwnerId $ownerId"
        try {
            repoResult = this.exposureRepository.findByOwnerId(ownerId)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findByOwnerId $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findByOwnerId"
        serviceResult
    }

    ServiceResult unGroup(Long clientId) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - ungGroup $clientId"
        try {
            repoResult = this.exposureRepository.unGroup(clientId)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - ungGroup $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - ungGroup"
        serviceResult
    }

    ServiceResult unGroup(Exposure exposure, Long clientId) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - ungGroup $clientId"
        try {
            repoResult = this.exposureRepository.unGroup(exposure, clientId)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - ungGroup $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - ungGroup"
        serviceResult
    }

    ServiceResult findByGroupedOwnerId(Long ownerId) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - findByGroupedOwnerId $ownerId"
        try {
            repoResult = this.exposureRepository.findByGroupedOwnerId(ownerId)
            serviceResult.success = true
            serviceResult.result = repoResult
        } catch (RispoException re) {
            log.info "RispoException - findByGroupedOwnerId $re.message"
            serviceResult.success = false
            serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
        }
        log.info "ok - findByGroupedOwnerId"
        serviceResult
    }

    ServiceResult delete(Exposure entityIn) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - delete $entityIn"
        try {
            repoResult = this.exposureRepository.delete(entityIn)
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

    ServiceResult save(Exposure entityIn) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - save $entityIn"
        try {
            repoResult = this.exposureRepository.save(entityIn)
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

    ServiceResult save(List<Exposure> entities) {

        ServiceResult serviceResult = new ServiceResult()
        def repoResult
        log.info "ok - save $entities"
        try {
            repoResult = this.exposureRepository.save(entities)
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
}
