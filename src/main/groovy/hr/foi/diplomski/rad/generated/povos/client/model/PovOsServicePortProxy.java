package hr.foi.diplomski.rad.generated.povos.client.model;

import java.net.URL;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class PovOsServicePortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private PovOsServiceService _service = null;
        private PovOsService _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new PovOsServiceService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (PovOsServiceService)ctx.lookup("java:comp/env/service/PovOsServiceService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new PovOsServiceService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getPovOsServicePort();
        }

        public PovOsService getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("", "PovOsServicePort");
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

    public PovOsServicePortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public PovOsServicePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public void provjera() {
        _getDescriptor().getProxy().provjera();
    }

    public Grupa dohvatiGrupuPrim(String identifikator, String grupaFlg) {
        return _getDescriptor().getProxy().dohvatiGrupuPrim(identifikator,grupaFlg);
    }

    public Grupa dohvatiGrupuPrimArhiva(String identifikator, String grupaFlg, String datum) {
        return _getDescriptor().getProxy().dohvatiGrupuPrimArhiva(identifikator,grupaFlg,datum);
    }

    public List<Grupa> pretragaGrupaPoNazivu(String naziv) {
        return _getDescriptor().getProxy().pretragaGrupaPoNazivu(naziv);
    }

    public List<Grupa> dohvatiGrupu(String brojRegistra) {
        return _getDescriptor().getProxy().dohvatiGrupu(brojRegistra);
    }

    public PosOdnosSBankom uPosOdnosuSBankom(String brojRegistra) {
        return _getDescriptor().getProxy().uPosOdnosuSBankom(brojRegistra);
    }

    public List<MpoGroup> getGroupsByRegNum(String regNum) {
        return _getDescriptor().getProxy().getGroupsByRegNum(regNum);
    }

    public List<String> getRegNumsByGroup(String kpo) {
        return _getDescriptor().getProxy().getRegNumsByGroup(kpo);
    }

    public Boolean verificirajGrupu(String kpoGrupe, String radnikVerifikacije) {
        return _getDescriptor().getProxy().verificirajGrupu(kpoGrupe,radnikVerifikacije);
    }

    public CkRelResponse getRelationshipData(List<Relationship> relationships) {
        return _getDescriptor().getProxy().getRelationshipData(relationships);
    }

    public CkRelResponse saveCKRelationships(Relationship newRelationships) {
        return _getDescriptor().getProxy().saveCKRelationships(newRelationships);
    }

    public MpoGroupResponse getGroupData(String kpo, String brRegistra) {
        return _getDescriptor().getProxy().getGroupData(kpo,brRegistra);
    }

    public MpoGroupResponse getFullGroupData(String kpo, String brRegistra) {
        return _getDescriptor().getProxy().getFullGroupData(kpo,brRegistra);
    }

    public List<Grupa> getClientGroups(String regNum) {
        return _getDescriptor().getProxy().getClientGroups(regNum);
    }

}
