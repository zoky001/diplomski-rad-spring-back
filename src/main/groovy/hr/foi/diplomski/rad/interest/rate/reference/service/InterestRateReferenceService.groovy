package hr.foi.diplomski.rad.interest.rate.reference.service

import hr.foi.diplomski.rad.interest.rate.reference.repository.InterestRateReferenceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException

@Slf4j
@Service
@TypeChecked
class InterestRateReferenceService {

    @Autowired
	InterestRateReferenceRepository interestRateReferenceRepository
    
    InterestRateReferenceService() {
	super()
	this.interestRateReferenceRepository = interestRateReferenceRepository
    }

    /**
     * getReferenceByName
     * @param inRate
     * @return
     */
    ServiceResult getReferenceByName(String inRate)  {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - getReferenceByName $inRate"
	try  {
	    repoResult = this.interestRateReferenceRepository.getReferenceByName(inRate)
	    serviceResult.success = true
	    serviceResult.result = repoResult
	} catch (RispoException re) {
	    log.info "RispoException - getReferenceByName $re.message"
	    serviceResult.success = false
	    serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
	}
	log.info "ok - getReferenceByName"
	serviceResult
    }

    /**
     * getEntries
     * @return
     */
    ServiceResult getEntries()  {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - getEntries "
	try  {
	    repoResult = this.interestRateReferenceRepository.getEntries()
	    serviceResult.success = true
	    serviceResult.result = repoResult
	} catch (RispoException re) {
	    log.info "RispoException - getEntries $re.message"
	    serviceResult.success = false
	    serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
	}
	log.info "ok - getEntries"
	serviceResult
    }
}
