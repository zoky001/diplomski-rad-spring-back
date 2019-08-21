//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.sova.client.model.model;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "SOVAserviceControllerService", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", wsdlLocation = "WEB-INF/wsdl/SOVAserviceControllerService.wsdl")
public class SOVAserviceControllerService
    extends Service
{

    private final static URL SOVASERVICECONTROLLERSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOVASERVICECONTROLLERSERVICE_EXCEPTION;
    private final static QName SOVASERVICECONTROLLERSERVICE_QNAME = new QName("http://controller.web.SOVAServices.zaba.hr/", "SOVAserviceControllerService");

    static {
            SOVASERVICECONTROLLERSERVICE_WSDL_LOCATION = SOVAserviceControllerService.class.getResource("/WEB-INF/wsdl/SOVAserviceControllerService.wsdl");
        WebServiceException e = null;
        if (SOVASERVICECONTROLLERSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/SOVAserviceControllerService.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        SOVASERVICECONTROLLERSERVICE_EXCEPTION = e;
    }

    public SOVAserviceControllerService() {
        super(__getWsdlLocation(), SOVASERVICECONTROLLERSERVICE_QNAME);
    }

    public SOVAserviceControllerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOVASERVICECONTROLLERSERVICE_QNAME, features);
    }

    public SOVAserviceControllerService(URL wsdlLocation) {
        super(wsdlLocation, SOVASERVICECONTROLLERSERVICE_QNAME);
    }

    public SOVAserviceControllerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOVASERVICECONTROLLERSERVICE_QNAME, features);
    }

    public SOVAserviceControllerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOVAserviceControllerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SOVAserviceControllerDelegate
     */
    @WebEndpoint(name = "SOVAserviceControllerPort")
    public SOVAserviceControllerDelegate getSOVAserviceControllerPort() {
        return super.getPort(new QName("http://controller.web.SOVAServices.zaba.hr/", "SOVAserviceControllerPort"), SOVAserviceControllerDelegate.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOVAserviceControllerDelegate
     */
    @WebEndpoint(name = "SOVAserviceControllerPort")
    public SOVAserviceControllerDelegate getSOVAserviceControllerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://controller.web.SOVAServices.zaba.hr/", "SOVAserviceControllerPort"), SOVAserviceControllerDelegate.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOVASERVICECONTROLLERSERVICE_EXCEPTION!= null) {
            throw SOVASERVICECONTROLLERSERVICE_EXCEPTION;
        }
        return SOVASERVICECONTROLLERSERVICE_WSDL_LOCATION;
    }

}
