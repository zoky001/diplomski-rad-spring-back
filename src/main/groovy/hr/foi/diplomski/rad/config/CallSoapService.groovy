package hr.foi.diplomski.rad.config



import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.Unmarshaller
// import javax.xml.soap.SOAPException
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamReader

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import com.fasterxml.jackson.databind.ObjectMapper

import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.zaba.emaframework.core.util.ModelUtil
import wslite.http.auth.HTTPBasicAuthorization
import wslite.soap.SOAPClient
import wslite.soap.SOAPClientException
import wslite.soap.SOAPFaultException
import wslite.soap.SOAPResponse


@Slf4j
@Service
class CallSoapService {

    @Value('${service.connectTimeout}')
    String connectTimeout

    @Value('${service.readTimeout}')
    String readTimeout

    String serviceSuffix = "Response"
    String startElementTagName = "return"
    String soapServiceErrorPrefix = "soapenv:Server - "
    /**
     *
     */
    CallSoapService() {
	// super()
	// this.soapClient = new SOAPClient()
    }

    /**
     * calls service and returns soap response
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param functionName - service function name for request
     * @param params - map or object model with params
     * @return soap response
     * @throws Exception
     */

    def callSoap(String url, String SCHEMA, String functionName, def params) throws Exception {


	def response

	try {
	    if(params instanceof Map) {
		response=callSoapWithMap(url, SCHEMA, functionName, params)
	    }
	    else {
		response=callSoapWithObject(url, SCHEMA, functionName, params)
	    }
	} catch (Exception e) {
	    //println "Error when calling web service: $e.message from $url with service function name $functionName"
	    log.error "callSoap Error when calling web service: $e.message from $url with service function name $functionName"

	    throw e
	}

	response
    }
    
    /**
     * calls service and returns soap response
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param functionName - service function name for request
     * @param params - map or object model with params
     * @param username
     * @param password
     * @return soap response
     * @throws Exception
     */

    def callSoapBasicAuthorization(String url, String SCHEMA, String functionName, def params, String username, String password) throws Exception {


	def response

	try {
	    if(params instanceof Map) {
		response=callSoapWithMapBasicAuthorization(url, SCHEMA, functionName, params, username, password)
	    }
	    else {
		response=callSoapWithObjectBasicAuthorization(url, SCHEMA, functionName, params, username, password)
	    }
	} catch (Exception e) {
	    //println "Error when calling web service: $e.message from $url with service function name $functionName"
	    log.error "callSoapBasicAuthorization Error when calling web service: $e.message from $url with service function name $functionName"

	    throw e
	}

	response
    }

    /**
     * converts soap response to plain java object
     * @param response - soap response
     * @param responseClass - generated request
     * @return plain java object
     * @throws Exception
     */
    def convertToPojo(SOAPResponse response, def responseClass) throws Exception {

	def ret

	def annots
	try {
	    annots = extractResponseAnnotationNameFromClass(responseClass)
	} catch (Exception e) {
	    log.info "Error when extracting annotation name from class in method convertToPojo: $e.message"
	    throw e
	}

	try	{
	    JAXBContext context = JAXBContext.newInstance(responseClass)
	    Unmarshaller unmarshaller = context.createUnmarshaller()
	    XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(response.getText()))

	    JAXBElement jaxElement = null

	    while(reader.hasNext()) {

		if(reader.isStartElement() && reader.getLocalName().equalsIgnoreCase(annots)) {
		    break
		}
		reader.next()
	    }

	    jaxElement = unmarshaller.unmarshal(reader, responseClass)
	    ret = jaxElement.getValue()

	    reader.close()
	} catch (Exception e) {
	    log.info  "convertToPojo Looking for ${annots.uncapitalize()}"
	    log.info "convertToPojo Error when converting to POJO: $e.message"
	    throw e
	}

	ret
    }

    /**
     * calls service returns java object
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param params - map with get params from url
     * @param responseClass - generated request
     * @return plain java object
     * @throws Exception
     */
    def callSoapAsPojo(String url, String SCHEMA, def params, def responseClass, def requestClass = null) throws Exception {

	def ret

	String annots = ""
	try {
	    if(requestClass != null) {
		annots = extractRequestAnnotationNameFromClass(requestClass)
	    } else {
		annots = extractRequestAnnotationNameFromClass(responseClass)
	    }
	} catch (Exception e) {
	    log.info "Error when extracting annotation name from class in method callSoapAsPojo: $e.message"
	    throw e
	}

	def response
	try {

	    if (annots.equals("")) {
		log.info "callSoapAsPojo annots is empty url service $url"
		throw new Exception ("annots is empty url service $url")
	    } else {
		response = callSoap(url, SCHEMA, annots, params)
		ret = convertToPojo(response, responseClass)
	    }
	} catch (Exception e) {
	    log.info "Error when calling service and converting to POJO in method callSoapAsPojo: $e.message"
	    throw e
	}

	ret
    }
    
    /**
     * calls service returns java object
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param params - map with get params from url
     * @param responseClass - generated request
     * @param username
     * @param password
     * @return plain java object
     * @throws Exception
     */
    def callSoapAsPojoBasicAuthorization(String url, String SCHEMA, def params, def responseClass, def requestClass = null, String username, String password) throws Exception {

	def ret

	String annots = ""
	try {
	    if(requestClass != null) {
		annots = extractRequestAnnotationNameFromClass(requestClass)
	    } else {
		annots = extractRequestAnnotationNameFromClass(responseClass)
	    }
	} catch (Exception e) {
	    log.info "Error when extracting annotation name from class in method callSoapAsPojo: $e.message"
	    throw e
	}

	def response
	try {

	    if (annots.equals("")) {
		log.info "callSoapAsPojoBasicAuthorization annots is empty url service $url"
		throw new Exception ("annots is empty url service $url")
	    } else {
		response = callSoapBasicAuthorization(url, SCHEMA, annots, params, username, password)
		ret = convertToPojo(response, responseClass)
	    }
	} catch (Exception e) {
	    log.info "Error when calling service and converting to POJO in method callSoapAsPojo: $e.message"
	    throw e
	}

	ret
    }

    /**
     * calls service and returns full service result
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param params - map with get params from url
     * @param responseClass - generated request
     * @return service result class
     */
    ServiceResult callSoapServiceResult(String url, String SCHEMA, def params, def responseClass) {

	ServiceResult serviceResult
	def ret

	try{

	    ret = callSoapAsPojo(url, SCHEMA, params, responseClass)
	} catch (Exception e) {
	    //println "$e.message"
	    serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["callSoapService.error"], errorMessageTextList: [filterSoapErrorMessage("$e.message")])
	    log.error "callSoapServiceResult Error when calling web service: $e.message service url: $url"
	    return serviceResult
	}

	return serviceResult = new ServiceResult(success: true, result: ret)
    }
    
    /**
     * calls service and returns full service result
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param params - map with get params from url
     * @param responseClass - generated request
     * @param usernme
     * @param password
     * @return service result class
     */
    ServiceResult callSoapServiceResultBasicAuthorization(String url, String SCHEMA, def params, def responseClass, String username, String password) {

	ServiceResult serviceResult
	def ret

	try{

	    ret = callSoapAsPojoBasicAuthorization(url, SCHEMA, params, responseClass, username, password)
	} catch (Exception e) {
	    //println "$e.message"
	    serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["callSoapService.error"], errorMessageTextList: [filterSoapErrorMessage("$e.message")])
	    log.error "callSoapServiceResultBasicAuthorization Error when calling web service: $e.message service url: $url"
	    return serviceResult
	}

	return serviceResult = new ServiceResult(success: true, result: ret)
    }

    /**
     * calls service and returns soap response when we have object model class
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param functionName - service function name for request
     * @param command - object model with get object from POST request
     * @return soap response
     * @throws Exception
     */
    def callSoapWithObject(String url, String SCHEMA, String functionName, def command, Map params = null) throws Exception {

	SOAPClient soapClient = new SOAPClient(url)
	Map<String, String> parameters = [SOAPAction: "", connectTimeout: Integer.parseInt(connectTimeout), readTimeout: Integer.parseInt(readTimeout)]
	if(params != null) {
	    parameters.putAll(params)
	}

	//Object to JSON in String
	try{
	    ObjectMapper mapper = new ObjectMapper()
	    String jsonInString = mapper.writeValueAsString(command)
	    if(jsonInString.length()<3500) {
		log.info "\ncallSoapWithObject: url: $url  \nInvoking ServiceAction: $functionName \nParametri:" + jsonInString + "\n"
	    }
	}catch(Exception e){
	    log.info "Reuest object to log error"
	}


	// dlwmin
	def response
	try {
	    response = soapClient.send(parameters){
		envelopeAttributes "xmlns:ns2": "${SCHEMA}"
		body { "ns2:$functionName"(setObjectRequest(command))}
	    }

	    if(response.httpResponse.contentAsString.length()<3500) {
		log.info "callSoapWithObject: Received response: $response.httpResponse.contentAsString"
	    }

	} catch (SOAPClientException sce) {
	    log.error "callSoapWithObject: Error when calling web service: $sce.message from $url with service function name $functionName request: $sce.request"
	    log.error "callSoapWithObject: Error when calling web service: $sce.message from $url with service function name $functionName response: $sce.response"
	    throw new Exception(sce.getMessage())
	} catch (SOAPFaultException sfe) {
	    log.error "callSoapWithObject: Error when calling web service: $sfe.message from $url with service function name $functionName request: $sfe.request"
	    log.error "callSoapWithObject: Error when calling web service: $sfe.message from $url with service function name $functionName response: $sfe.response"
	    throw new Exception(sfe.getMessage())
	} /*catch (SOAPException e) {
	    log.error "callSoapWithObject: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw new Exception(e.getMessage())
	} */catch (Exception e) {
	    log.error "callSoapWithObject: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw e
	}

	response
    }
    
    /**
     * calls service and returns soap response when we have object model class
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param functionName - service function name for request
     * @param command - object model with get object from POST request
     * @param username
     * @param password
     * @return soap response
     * @throws Exception
     */
    def callSoapWithObjectBasicAuthorization(String url, String SCHEMA, String functionName, def command, Map params = null, String lo, String pa) throws Exception {

	SOAPClient soapClient = new SOAPClient(url)
	Map<String, String> parameters = [SOAPAction: "", connectTimeout: Integer.parseInt(connectTimeout), readTimeout: Integer.parseInt(readTimeout)]
	if(params != null) {
	    parameters.putAll(params)
	}

	//Object to JSON in String
	try{
	    ObjectMapper mapper = new ObjectMapper()
	    String jsonInString = mapper.writeValueAsString(command)
	    if(jsonInString.length()<3500) {
		log.info "\ncallSoapWithObjectBasicAuthorization: url: $url  \nInvoking ServiceAction: $functionName \nParametri:" + jsonInString + "\n"
	    }
	}catch(Exception e){
	    log.info "Reuest object to log error"
	}

	// autorizacija
	if (lo != null && lo.length() > 0 && pa != null)  {
	    soapClient.httpClient.authorization = new HTTPBasicAuthorization(lo, pa)
	}

	// dlwmin
	def response
	try {
	    response = soapClient.send(parameters){
		envelopeAttributes "xmlns:ns2": "${SCHEMA}"
		body { "ns2:$functionName"(setObjectRequest(command))}
	    }

	    if(response.httpResponse.contentAsString.length()<3500) {
		log.info "callSoapWithObjectBasicAuthorization: Received response: $response.httpResponse.contentAsString"
	    }

	} catch (SOAPClientException sce) {
	    log.error "callSoapWithObjectBasicAuthorization: Error when calling web service: $sce.message from $url with service function name $functionName request: $sce.request"
	    log.error "callSoapWithObjectBasicAuthorization: Error when calling web service: $sce.message from $url with service function name $functionName response: $sce.response"
	    throw new Exception(sce.getMessage())
	} catch (SOAPFaultException sfe) {
	    log.error "callSoapWithObjectBasicAuthorization: Error when calling web service: $sfe.message from $url with service function name $functionName request: $sfe.request"
	    log.error "callSoapWithObjectBasicAuthorization: Error when calling web service: $sfe.message from $url with service function name $functionName response: $sfe.response"
	    throw new Exception(sfe.getMessage())
	} /*catch (SOAPException e) {
	    log.error "callSoapWithObjectBasicAuthorization: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw new Exception(e.getMessage())
	} */catch (Exception e) {
	    log.error "callSoapWithObjectBasicAuthorization: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw e
	}

	response
    }

    /**
     * calls service and returns soap response when we have Map params
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param functionName - service function name for request
     * @param command - map with get params from url
     * @return soap response
     * @throws Exception
     */
    def callSoapWithMap(String url, String SCHEMA, String functionName, Map<String, String> command, Map params = null) throws Exception {

	SOAPClient soapClient = new SOAPClient(url)
	Map<String, String> parameters = [SOAPAction: "", connectTimeout: Integer.parseInt(connectTimeout), readTimeout: Integer.parseInt(readTimeout)];
	if (params != null) {
	    parameters.putAll(params);
	}


	try {
	    if (command.toString().length() < 3500) {
		log.info "\ncallSoapWithMap: url: $url  \nInvoking ServiceAction: $functionName \n Parametri:" + command.toMapString() + ""
	    }
	} catch (Exception e) {
	    log.info "callSoapWithMap log error"
	}


	def response
	try {
	    response = soapClient.send(parameters) {
		envelopeAttributes "xmlns:ns2": "${SCHEMA}"
		body {
		    "ns2:$functionName" {
			command.each {
			    "$it.key"(it.value)
			}
		    }
		}
	    }

	    if(response.httpResponse.contentAsString.length()<3500) {	log.info "callSoapWithMap: Received response: $response.httpResponse.contentAsString"}

	} catch (SOAPClientException sce) {
	    log.error "callSoapWithMap: SoapClientUrl: $soapClient.serviceURL"
	    log.error "callSoapWithMap: Error when calling web service: $sce.message from $url with service function name $functionName request: $sce.request"
	    log.error "callSoapWithMap: Error when calling web service: $sce.message from $url with service function name $functionName response: $sce.response"
	    throw new Exception(sce.getMessage())
	} catch (SOAPFaultException sfe) {
	    log.error "callSoapWithMap: Error when calling web service: $sfe.message from $url with service function name $functionName request: $sfe.request"
	    log.error "callSoapWithMap: Error when calling web service: $sfe.message from $url with service function name $functionName response: $sfe.response"
	    throw new Exception(sfe.getMessage())
	} /*catch (SOAPException e) {
	    log.error "callSoapWithMap: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw new Exception(e.getMessage())
	} */catch (Exception e) {
	    log.error "callSoapWithMap: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw e
	}

	response
    }
    
    /**
     * calls service and returns soap response when we have Map params
     * @param url - service adress
     * @param SCHEMA - service response schema
     * @param functionName - service function name for request
     * @param command - map with get params from url
     * @param username
     * @param password
     * @return soap response
     * @throws Exception
     */
    def callSoapWithMapBasicAuthorization(String url, String SCHEMA, String functionName, Map<String, String> command, Map params = null, String lo, String pa) throws Exception {

	SOAPClient soapClient = new SOAPClient(url)
	Map<String, String> parameters = [SOAPAction: "", connectTimeout: Integer.parseInt(connectTimeout), readTimeout: Integer.parseInt(readTimeout)];
	if (params != null) {
	    parameters.putAll(params);
	}


	try {
	    if (command.toString().length() < 3500) {
		log.info "\ncallSoapWithMap: url: $url  \nInvoking ServiceAction: $functionName \n Parametri:" + command.toMapString() + ""
	    }
	} catch (Exception e) {
	    log.info "callSoapWithMap log error"
	}

	// autorizacija
	if (lo != null && lo.length() > 0 && pa != null)  {
	    soapClient.httpClient.authorization = new HTTPBasicAuthorization(lo, pa)
	}
	
	def response
	try {
	    response = soapClient.send(parameters) {
		envelopeAttributes "xmlns:ns2": "${SCHEMA}"
		body {
		    "ns2:$functionName" {
			command.each {
			    "$it.key"(it.value)
			}
		    }
		}
	    }

	    if(response.httpResponse.contentAsString.length()<3500) {	log.info "callSoapWithMap: Received response: $response.httpResponse.contentAsString"}

	} catch (SOAPClientException sce) {
	    log.error "callSoapWithMapBasicAuthorization: SoapClientUrl: $soapClient.serviceURL"
	    log.error "callSoapWithMapBasicAuthorization: Error when calling web service: $sce.message from $url with service function name $functionName request: $sce.request"
	    log.error "callSoapWithMapBasicAuthorization: Error when calling web service: $sce.message from $url with service function name $functionName response: $sce.response"
	    throw new Exception(sce.getMessage())
	} catch (SOAPFaultException sfe) {
	    log.error "callSoapWithMapBasicAuthorization: Error when calling web service: $sfe.message from $url with service function name $functionName request: $sfe.request"
	    log.error "callSoapWithMapBasicAuthorization: Error when calling web service: $sfe.message from $url with service function name $functionName response: $sfe.response"
	    throw new Exception(sfe.getMessage())
	} /*catch (SOAPException e) {
	    log.error "callSoapWithMapBasicAuthorization: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw new Exception(e.getMessage())
	}*/ catch (Exception e) {
	    log.error "callSoapWithMapBasicAuthorization: Error when calling web service: $e.message from $url with service function name $functionName"
	    throw e
	}

	response
    }

    /**
     * parse object and create Closure for soap request
     * @param command - object to parse
     * @return	Closure
     */
    private Closure setObjectRequest(def command) {
	Closure k= {
	    try {
		ModelUtil.toPropertyMap(command).findAll({key, value -> value != null}).eachWithIndex {
		    //map all properties like <property name, property value
		    //if property is an array, call recursion with every item
		    if(it.value instanceof ArrayList) {
			it.value.eachWithIndex { id, i ->
			    if(it.value[i].getClass().name.contains("hr.zaba")){
				// log.error "1 $it.key - $it.value"
				"$it.key"(setObjectRequest(it.value[i]))	//map property name with Closure from return
			    }
			    else {
				// log.error "2 $it.key - $it.value"
				"$it.key"(it.value[i])
			    }
			}
		    }
		    //if property is our class, call recursion
		    else if(it.value.getClass().name.contains("hr.zaba")) {
			// log.error "3 $it.key - $it.value"
			"$it.key"(setObjectRequest(it.value))					//map property name with Closure from return
		    }
		    else {
			"$it.key"(it.value)											//wrap all other values
			log.error "4 $it.key - $it.value"
		    }
		}
	    }catch(Exception e) {
		// println e.message
		log.error "CallSoapService.setObjectRequest error $e.message"
	    }
	}
	return k
    }

    /**
     * filterSoapErrorMessage
     * @param mess
     * @return returns filtered soap service message
     */
    String filterSoapErrorMessage(String mess) {

	String out = ""
	if (mess != null && !mess.isEmpty() && mess.startsWith(soapServiceErrorPrefix)){
	    out = mess.replace(soapServiceErrorPrefix, "")
	}
	out
    }

    /**
     * extractRequestAnnotationNameFromClass
     * @param responseClass - entry class
     * @return anotation name
     * @throws Exception
     */
    private def extractRequestAnnotationNameFromClass(def responseClass) throws Exception {

	def annots
	try {
	    annots= responseClass.getAnnotationsByType(javax.xml.bind.annotation.XmlType).getAt(0).name().replace(serviceSuffix, "")
	} catch (Exception e1) {
	    try {
		annots= responseClass.getSimpleName().uncapitalize().replace(serviceSuffix, "")
	    } catch (Exception e2) {

		log.info "Error when getting annotation from response class: $e2.message"
		throw e2
	    }
	}

	annots
    }


    /**
     * extractResponseAnnotationNameFromClass
     * @param responseClass - entry class
     * @return anotation name
     * @throws Exception
     */
    private def extractResponseAnnotationNameFromClass(def responseClass) throws Exception {

	def annots
	try {
	    annots= responseClass.getAnnotationsByType(javax.xml.bind.annotation.XmlType).getAt(0).name()
	} catch (Exception e1) {
	    try {
		annots= responseClass.getSimpleName().uncapitalize()
	    } catch (Exception e2) {

		log.info "Error when getting annotation from response class: $e2.message"
		throw e2
	    }
	}

	annots
    }
    
    
    /**
     * collectionToString
     * @param collection
     * @return
     */
    static String collectionToString(Collection<String> collection) {
	StringBuilder sb = new StringBuilder()
	for (String s : collection) {
	    sb.append(s + "','")
	}
	if (sb.length() != 0) {
	    sb.setLength(sb.length() - 3);
	}
	sb.toString()
    }
}
