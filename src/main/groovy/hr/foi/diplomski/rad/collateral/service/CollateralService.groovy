package hr.foi.diplomski.rad.collateral.service

import hr.foi.diplomski.rad.collateral.repository.CollateralRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.exposure.Collateral

@Slf4j
@Service
@TypeChecked
class CollateralService {

    @Autowired
	CollateralRepository collateralRepository

    CollateralService(CollateralRepository collateralRepository) {
	super()
	this.collateralRepository = collateralRepository
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
	try  {
	    repoResult = this.collateralRepository.findByOwnerId(ownerId)
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
    /**
     *
     * @param entityIn
     * @return
     */
    ServiceResult delete(Collateral entityIn) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - delete $entityIn"
	try  {
	    repoResult = this.collateralRepository.delete(entityIn)
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

    ServiceResult save(Collateral entityIn) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - save $entityIn"
	try  {
	    repoResult = this.collateralRepository.save(entityIn)
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

    ServiceResult save(List<Collateral> entities) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - save $entities"
	try  {
	    repoResult = this.collateralRepository.save(entities)
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
