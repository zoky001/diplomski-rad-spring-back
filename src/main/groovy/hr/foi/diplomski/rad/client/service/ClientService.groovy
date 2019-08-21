package hr.foi.diplomski.rad.client.service

import hr.foi.diplomski.rad.client.repository.ClientRepository
import hr.foi.diplomski.rad.model.client.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException

@Slf4j
@Service
@TypeChecked
class ClientService {

    @Autowired
	ClientRepository clientRepository

    ClientService(ClientRepository clientRepository) {
	super()
	this.clientRepository = clientRepository
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
	    repoResult = this.clientRepository.findByOwnerId(ownerId)
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
     * dohvatiVrstaOsobe
     * @param brRegistra
     * @return
     */
    ServiceResult dohvatiVrstaOsobe(String brRegistra) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - dohvatiVrstaOsobe $brRegistra"
	try  {
	    repoResult = this.clientRepository.dohvatiVrstaOsobe(brRegistra)
	    serviceResult.success = true
	    serviceResult.result = repoResult
	} catch (RispoException re) {
	    log.info "RispoException - dohvatiVrstaOsobe $re.message"
	    serviceResult.success = false
	    serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
	}
	log.info "ok - dohvatiVrstaOsobe"
	serviceResult
    }

    /**
     * 
     * @param entityIn
     * @return
     */
    ServiceResult delete(Client entityIn) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - delete $entityIn"
	try  {
	    repoResult = this.clientRepository.delete(entityIn)
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

    ServiceResult save(Client entityIn) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - save $entityIn"
	try  {
	    repoResult = this.clientRepository.save(entityIn)
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

    ServiceResult save(List<Client> entities) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - save $entities"
	try  {
	    repoResult = this.clientRepository.save(entities)
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

    ServiceResult getClientsForGroup(Long groupId) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - getClientsForGroup $groupId"
	try  {
	    repoResult = this.clientRepository.getClientsForGroup(groupId)
	    serviceResult.success = true
	    serviceResult.result = repoResult
	} catch (RispoException re) {
	    log.info "RispoException - getClientsForGroup $re.message"
	    serviceResult.success = false
	    serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
	}
	log.info "ok - getClientsForGroup"
	serviceResult
    }


    ServiceResult unGroup(Client entityIn) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - ungGroup $entityIn"
	try  {
	    repoResult = this.clientRepository.unGroup(entityIn)
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

    ServiceResult setPrimaryMember(Client entityIn) {

	ServiceResult serviceResult = new ServiceResult()
	def repoResult
	log.info "ok - setPrimaryMember $entityIn"
	try  {
	    repoResult = this.clientRepository.setPrimaryMember(entityIn)
	    serviceResult.success = true
	    serviceResult.result = repoResult
	} catch (RispoException re) {
	    log.info "RispoException - setPrimaryMember $re.message"
	    serviceResult.success = false
	    serviceResult.errorMessageCodeList[0] = "rispo.error"; serviceResult.errorMessageTextList[0] = re.message
	}
	log.info "ok - setPrimaryMember"
	serviceResult
    }
}
