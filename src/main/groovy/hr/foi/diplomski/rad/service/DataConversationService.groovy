package hr.foi.diplomski.rad.service

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.MockMapping
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

import javax.servlet.http.HttpServletRequest
import java.util.stream.Collectors

@Slf4j
@Service
class DataConversionService implements GroovyObject {

    @Value('${mySettings.mockServices.enabled}')
    private boolean enabledMock

    @Autowired
    private HttpServletRequest request

    Response parseServiceResult(ServiceResult serviceResult) {
        Response obj = new Response()
        obj.success = serviceResult.success

        if (serviceResult.result) {
            obj.data = serviceResult.result
        }

        if (serviceResult.errorMessageCodeList) {
            obj.errorMessageCodeList = serviceResult.errorMessageCodeList
        }

        if (serviceResult.errorMessageTextList) {
            obj.errorMessageTextList = serviceResult.errorMessageTextList
        }


        def path = MockMapping.getJsonPath(request.requestURI)
        if (enabledMock && path) {
            return parseJSON("/__files/rispo/" + path)
        }
        obj
    }

    private parseJSON(String fileName = "data.json") {

        try {

//            File resource = new ClassPathResource(fileName).getFile()
//            String employees = new String(
//                    Files.readAllBytes(resource.toPath()))
//            def jsonSlurper = new JsonSlurper()
//            def account = jsonSlurper.parseText(employees) as Response
//            return account


            BufferedReader reader
            try {
                InputStream resource1 = new ClassPathResource(
                        fileName).getInputStream()
                reader = new BufferedReader(new InputStreamReader(resource1))
                String employees1 = reader.lines()
                        .collect(Collectors.joining("\n"));
                def jsonSlurper = new JsonSlurper()
                def account = jsonSlurper.parseText(employees1) as Response
                return account
            }catch(e){

            }finally{
                reader.close()
            }


        } catch (e) {
            log.error("Greska dohvaćanja JSON datoteke s nazivom: " + fileName)
            log.error("Greska: " + e.message)
        }

    }

    class Response {
        boolean success
        Object data
        List<String> errorMessageCodeList
        List<String> errorMessageTextList
    }


}
