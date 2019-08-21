package hr.foi.diplomski.rad.generated.irws.client.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.List;

public class IRWSPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private IRWSService _service = null;
        private IRWS _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new IRWSService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (IRWSService)ctx.lookup("java:comp/env/service/IRWSService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new IRWSService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getIRWSPort();
        }

        public IRWS getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("", "IRWSPort");
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

    public IRWSPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public IRWSPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public KlijentPodaci getRejting(String brRegistra, String kpo) {
        return _getDescriptor().getProxy().getRejting(brRegistra,kpo);
    }

    public RejtingGrupe getRejtingGrupe(String kpo) {
        return _getDescriptor().getProxy().getRejtingGrupe(kpo);
    }

    public String getKlijentRejtingFlag(String brRegistra) {
        return _getDescriptor().getProxy().getKlijentRejtingFlag(brRegistra);
    }

    public boolean povOsPonistenaRejtingVeza(String brRegistra, String brojRadnika) {
        return _getDescriptor().getProxy().povOsPonistenaRejtingVeza(brRegistra,brojRadnika);
    }

    public boolean povOsPromjenaHoldinga(String brRegistra, String brojRadnika) {
        return _getDescriptor().getProxy().povOsPromjenaHoldinga(brRegistra,brojRadnika);
    }

    public boolean povOsBrisanjeIzGrupe(String brRegistra, String brojRadnika) {
        return _getDescriptor().getProxy().povOsBrisanjeIzGrupe(brRegistra,brojRadnika);
    }

    public boolean povOsDodanUGrupu(String brRegistra, String brojRadnika) {
        return _getDescriptor().getProxy().povOsDodanUGrupu(brRegistra,brojRadnika);
    }

    public boolean povOsVerificiranaRejtingVeza(String brRegistra, String brojRadnika) {
        return _getDescriptor().getProxy().povOsVerificiranaRejtingVeza(brRegistra,brojRadnika);
    }

    public boolean povOsBrisanjeGrupe(String kpo, List<String> brojeviRegistraClanova, String brojRadnika) {
        return _getDescriptor().getProxy().povOsBrisanjeGrupe(kpo,brojeviRegistraClanova,brojRadnika);
    }

    public boolean promjenaRejtinga(String arg0, String arg1, String arg2, BigDecimal arg3) {
        return _getDescriptor().getProxy().promjenaRejtinga(arg0,arg1,arg2,arg3);
    }

    public VazeciRejting getVazeciRejting(String brRegistra, String kpo, String datum) {
        return _getDescriptor().getProxy().getVazeciRejting(brRegistra,kpo,datum);
    }

    public RejtingTrend getRejtingUclc(String brRegistra) {
        return _getDescriptor().getProxy().getRejtingUclc(brRegistra);
    }

    public RejtingUclc financijskiRejtingNeKlijenta(String maticniBroj) {
        return _getDescriptor().getProxy().financijskiRejtingNeKlijenta(maticniBroj);
    }

    public RejtingUclc izracunajUclcRejting(String brRegistra, List<Aop> aops) {
        return _getDescriptor().getProxy().izracunajUclcRejting(brRegistra,aops);
    }

    public RejtingUclc getVazeciRejtingUclc(String brRegistra) {
        return _getDescriptor().getProxy().getVazeciRejtingUclc(brRegistra);
    }

}
