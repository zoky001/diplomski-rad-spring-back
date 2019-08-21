package hr.foi.diplomski.rad.generated.sova.client.model.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.util.List;

public class SOVAserviceControllerPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private SOVAserviceControllerService _service = null;
        private SOVAserviceControllerDelegate _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new SOVAserviceControllerService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (SOVAserviceControllerService)ctx.lookup("java:comp/env/service/SOVAserviceControllerService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new SOVAserviceControllerService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getSOVAserviceControllerPort();
        }

        public SOVAserviceControllerDelegate getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://controller.web.SOVAServices.zaba.hr/", "SOVAserviceControllerPort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
    }

    public SOVAserviceControllerPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public SOVAserviceControllerPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public List<WsKorinikAutorizacijaoData> wSKorisnikAutorizacija(String arg0, String arg1) {
        return _getDescriptor().getProxy().wSKorisnikAutorizacija(arg0,arg1);
    }

    public List<WsKorisnikPOSifraData> wSKorisnikPOSifra(String arg0, String arg1, String arg2) {
        return _getDescriptor().getProxy().wSKorisnikPOSifra(arg0,arg1,arg2);
    }

    public List<WsKorisnikAplikacijaData> wSKorisnikAplikacija(String arg0, String arg1) {
        return _getDescriptor().getProxy().wSKorisnikAplikacija(arg0,arg1);
    }

    public String getTest() {
        return _getDescriptor().getProxy().getTest();
    }

    public List<WsZaposlenikPOData> wSFunkcijaPO(String arg0, List<WsFunkcijaPoParametri> arg1) {
        return _getDescriptor().getProxy().wSFunkcijaPO(arg0,arg1);
    }

    public List<WsAplikativneAkcijeData> wsAplikativneAkcije(String arg0, String arg1) {
        return _getDescriptor().getProxy().wsAplikativneAkcije(arg0,arg1);
    }

    public List<WsAplikativneAkcijePOData> wsAplikativneAkcijePO(String arg0, String arg1, String arg2, String arg3) {
        return _getDescriptor().getProxy().wsAplikativneAkcijePO(arg0,arg1,arg2,arg3);
    }

    public List<WsKorisnikAutorizacijaPOData> wsKorisnikAutorizacijaPO(String arg0, String arg1, String arg2, String arg3) {
        return _getDescriptor().getProxy().wsKorisnikAutorizacijaPO(arg0,arg1,arg2,arg3);
    }

    public List<WsKorisnikAutorizacijaPOData> wsKorisnikWidgetPO(String arg0, String arg1, String arg2, String arg3) {
        return _getDescriptor().getProxy().wsKorisnikWidgetPO(arg0,arg1,arg2,arg3);
    }

    public List<WsKorisniciFunkcijaPodredjeniData> wsKorisniciFunkcijaPodredjeni(String arg0, String arg1, String arg2) {
        return _getDescriptor().getProxy().wsKorisniciFunkcijaPodredjeni(arg0,arg1,arg2);
    }

    public List<WsKorisnikAutorizacijaPOData> wsKorisnikAutorizacijaDirektnaPO(String arg0, String arg1, String arg2, String arg3) {
        return _getDescriptor().getProxy().wsKorisnikAutorizacijaDirektnaPO(arg0,arg1,arg2,arg3);
    }

}
