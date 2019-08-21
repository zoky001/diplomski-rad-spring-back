package hr.foi.diplomski.rad.generated.tecaj.deviza.client.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class WSTecajDevizaPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private WSTecajDevizaService _service = null;
        private WSTecajDeviza _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new WSTecajDevizaService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (WSTecajDevizaService)ctx.lookup("java:comp/env/service/WSTecajDevizaService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new WSTecajDevizaService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getWSTecajDevizaPort();
        }

        public WSTecajDeviza getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://service.tecaj.zaba.hr/", "WSTecajDevizaPort");
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

    public WSTecajDevizaPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public WSTecajDevizaPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public Tecajevi getTecajZaPeriod(String oznDevize, String oznBurze, String datumOd, String datumDo) {
        return _getDescriptor().getProxy().getTecajZaPeriod(oznDevize,oznBurze,datumOd,datumDo);
    }

    public Izlaz1 getTecaj(String oznBurze, String oznDevize, String datum, String vrstaKlijenta, String jezik) {
        return _getDescriptor().getProxy().getTecaj(oznBurze,oznDevize,datum,vrstaKlijenta,jezik);
    }

    public Izlaz getDeviza(String oznDevize, String datum) {
        return _getDescriptor().getProxy().getDeviza(oznDevize,datum);
    }

    public Tecajevi getTecajevi(String oznBurze, String datum, String vrstaKlijenta, String jezik) {
        return _getDescriptor().getProxy().getTecajevi(oznBurze,datum,vrstaKlijenta,jezik);
    }

    public Devize popisValuta() {
        return _getDescriptor().getProxy().popisValuta();
    }

}
