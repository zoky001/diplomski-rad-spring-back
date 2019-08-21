package hr.foi.diplomski.rad.collateral.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.collateral.service.CollateralService
import hr.foi.diplomski.rad.model.exposure.Collateral
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/collateral")
@TypeChecked
class CollateralController {

	CollateralService collateralService
	DataConversionService dataConversionService

	@Autowired
	CollateralController(CollateralService collateralService,DataConversionService dataConversionService) {
		this.dataConversionService = dataConversionService
		this.collateralService=collateralService
	}

	@GetMapping("/findByOwnerId")
	def findByOwnerId(@RequestParam Long ownerId) {
		ServiceResult sr = collateralService.findByOwnerId(ownerId)
		return dataConversionService.parseServiceResult(sr)
	}

	@PostMapping("/delete")
	def delete(@RequestBody Collateral collateral) {
		ServiceResult sr = collateralService.delete(collateral)
		return dataConversionService.parseServiceResult(sr)
	}

	@PostMapping("/save")
	def save(@RequestBody Collateral collateral) {
		ServiceResult sr = collateralService.save(collateral)
		return dataConversionService.parseServiceResult(sr)
	}

	@PostMapping("/saveList")
	def save(@RequestBody List<Collateral> entities) {
		ServiceResult sr = collateralService.save(entities)
		return dataConversionService.parseServiceResult(sr)
	}
}
