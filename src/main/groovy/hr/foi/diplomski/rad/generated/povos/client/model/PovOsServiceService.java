//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.povos.client.model;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "PovOsServiceService", targetNamespace = "http://service.ze.zaba.hr/", wsdlLocation = "WEB-INF/wsdl/PovOsServiceService.wsdl")
public class PovOsServiceService
    extends Service
{

    private final static URL POVOSSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException POVOSSERVICESERVICE_EXCEPTION;
    private final static QName POVOSSERVICESERVICE_QNAME = new QName("http://service.ze.zaba.hr/", "PovOsServiceService");

    static {
            POVOSSERVICESERVICE_WSDL_LOCATION = PovOsServiceService.class.getResource("/WEB-INF/wsdl/PovOsServiceService.wsdl");
        WebServiceException e = null;
        if (POVOSSERVICESERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/PovOsServiceService.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        POVOSSERVICESERVICE_EXCEPTION = e;
    }

    public PovOsServiceService() {
        super(__getWsdlLocation(), POVOSSERVICESERVICE_QNAME);
    }

    public PovOsServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), POVOSSERVICESERVICE_QNAME, features);
    }

    public PovOsServiceService(URL wsdlLocation) {
        super(wsdlLocation, POVOSSERVICESERVICE_QNAME);
    }

    public PovOsServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, POVOSSERVICESERVICE_QNAME, features);
    }

    public PovOsServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PovOsServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PovOsService
     */
    @WebEndpoint(name = "PovOsServicePort")
    public PovOsService getPovOsServicePort() {
        return super.getPort(new QName("http://service.ze.zaba.hr/", "PovOsServicePort"), PovOsService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PovOsService
     */
    @WebEndpoint(name = "PovOsServicePort")
    public PovOsService getPovOsServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.ze.zaba.hr/", "PovOsServicePort"), PovOsService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (POVOSSERVICESERVICE_EXCEPTION!= null) {
            throw POVOSSERVICESERVICE_EXCEPTION;
        }
        return POVOSSERVICESERVICE_WSDL_LOCATION;
    }

}
