package hr.foi.diplomski.rad.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import groovy.json.JsonOutput
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import wslite.http.HTTP
import wslite.http.auth.HTTPBasicAuthorization
import wslite.rest.RESTClient
import wslite.rest.RESTClientException

@Slf4j
@Service
class CallRestService {
	@Value('${service.connectTimeout}')
	String connectTimeout = "15000"

	@Value('${service.readTimeout}')
	String readTimeout = "15000"

	/**
	 * 
	 */
	public CallRestService() {
	}

	/**
	 * calling REST service with POST request and returning ServiceResult
	 * @param url
	 * @param path
	 * @param body - body object
	 * @param cookies - should be null if don't use it (you can add ltpaToken2 in cookies, if you need it)
	 * @return ServiceResult
	 */
	ServiceResult callRestPOSTServiceResult(String url, String path, def body, def cookies) {

		ServiceResult serviceResult


		def result = null
		try {
			result = callRestPOST(url, path, body, cookies);
		} catch (RESTClientException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["callRestPOST.error"], errorMessageTextList: ["$e.message"])
			log.error "Error when calling web service: $e.message service url: $url$path"
			log.error "Request: $e.request"
			log.error "Response: $e.response"
			return serviceResult
		}

		serviceResult = new ServiceResult(success: true, result: result)
	}
	/**
	 * will force response charset to UTF-8
	 * @param closure
	 * @return
	 */
	def getWithDefaultCharsetUTF8(closure){
		def c = closure.clone()
		c.delegate = this
		HTTP.metaClass.'static'.getDEFAULT_CHARSET = { -> 'UTF-8' }
		def response = c()
		//HTTP.metaClass.'static'.getDEFAULT_CHARSET = { -> 'ISO-8859-1' }
		return response
}
	/**
	 * calling REST service with POST request
	 * @param url
	 * @param path
	 * @param body - body object
	 * @param cookies - should be null if don't use it (you can add ltpaToken2 in cookies, if you need it)
	 * @return Response
	 */
	def callRestPOST(String url, String path, def body,  def cookies) throws RESTClientException {
		def result = null
		if (url == null || url.trim().length()==0){
			log.error "Error when calling web service: Empty url for service service url: $url"
			return result
		} else {
			log.info "Calling service from url $url$path :"
		}
		String bodyJSON;
		if(body instanceof String) {
			bodyJSON = body
		} else {
			bodyJSON = JsonOutput.toJson(body)
		}
		Closure content;
		if (body) {
			content = {
				charset 'UTF-8'
				text bodyJSON
			};
		}
		
		RESTClient restClient = new RESTClient(url);
		
		result = getWithDefaultCharsetUTF8{restClient.post([path: path,
		headers: ['Content-Language':'hr-HR','Content-Type': 'application/json','Cache-Control': '[NO-CACHE]',
			'Cookie' : "$cookies"],
		connectTimeout: Integer.parseInt(connectTimeout),
		readTimeout: Integer.parseInt(readTimeout)], content
			)}?.json

		return result;
	}


	/**
	 * calling REST service with GET request and returning ServiceResult
	 * @param url
	 * @param query - map with query parameters
	 * @return ServiceResult
	 */
	ServiceResult callRestGETServiceResult(String url, Map<String,String> query, def cookies) {

		ServiceResult serviceResult

		def result = null
		try {
			result = callRestGET(url, query, cookies) 
		} catch (RESTClientException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["callRestGET.error"], errorMessageTextList: ["$e.message"])
			log.error "Error when calling web service: $e.message service url: $url"
			return serviceResult
		}
		serviceResult = new ServiceResult(success: true, result: result)
	}
	
	/**
	 * calling REST service with GET request, forced to UTF-8
	 * @param url
	 * @param query - map with query parameters
	 * @return Response
	 */
	def callRestGET(String url, Map<String,String> query, def cookies) throws RESTClientException {

		ServiceResult serviceResult

		if (url == null || url.trim().length()==0){
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["callRestGET.error"], errorMessageTextList: ["Empty url for service"])
			log.error "Error when calling web service: Empty url for service service url: $url"
			return serviceResult
		} else {
			log.info "Calling service from url $url"
		}

		def response = null
		
		RESTClient restClient = new RESTClient(url);
		response = getWithDefaultCharsetUTF8{restClient.get(query: query,
		headers: ["Content-Type": "application/json;charset=UTF-8", "Content-Language":"hr-HR",
			'Cookie' : "$cookies"],
		connectTimeout: Integer.parseInt(connectTimeout),
		readTimeout: Integer.parseInt(readTimeout))}?.json
		return response;
	}
	
	/**
	 * calling REST service with GET request and basic authorization
	 * @param url
	 * @param query - map with query parameters
	 * @return ServiceResult as type
	 */
	ServiceResult callRestGETWithBasicAuthorization(String url, Map<String,String> query, def cookies, String lo, String pa, String readTimeoutService) throws RESTClientException {

		if (url == null || url.trim().length()==0){
			ServiceResult serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["callRestGET.error"], errorMessageTextList: ["Empty url for service"])
			log.error "Error when calling web service: Empty url for service service url: $url"
			serviceResult.success = false
			return serviceResult
		} else {
			log.info "Calling service from url $url"
		}

		ServiceResult response = new ServiceResult()
		RESTClient restClient = new RESTClient(url);
		
		// autorizacija
		if (lo != null && lo.length() > 0 && pa != null)  {
			restClient.httpClient.authorization = new HTTPBasicAuthorization(lo, pa)
		}
		
		if (readTimeoutService == null) {
			readTimeoutService = readTimeout
		}
		
		response.result = restClient.get(query: query,
		headers: ["Content-Type": "application/json;charset=UTF-8",
			'Cookie' : "$cookies"],
		connectTimeout: Integer.parseInt(connectTimeout),
		readTimeout: Integer.parseInt(readTimeoutService))?.json
	
		response.success = true
		response;
	}
}
