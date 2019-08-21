package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

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

public class KlijentServisPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private KlijentServisService _service = null;
        private KlijentServis _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new KlijentServisService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (KlijentServisService)ctx.lookup("java:comp/env/service/KlijentServisService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new KlijentServisService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getKlijentServisPort();
        }

        public KlijentServis getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://service.zok.zaba.hr/", "KlijentServisPort");
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

    public KlijentServisPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public KlijentServisPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public KlijentPodatak podaciOKlijentu(String idKlijenta) throws ZokException_Exception {
        return _getDescriptor().getProxy().podaciOKlijentu(idKlijenta);
    }

    public IstekliPodaci podaciOKlijentuIstekli(String idKlijenta) throws ZokException_Exception {
        return _getDescriptor().getProxy().podaciOKlijentuIstekli(idKlijenta);
    }

    public Rezultat provjeriPodatkeOKlijentu(KlijentPodatak klijentPodaci, String brojRadnika) throws ZokException_Exception {
        return _getDescriptor().getProxy().provjeriPodatkeOKlijentu(klijentPodaci,brojRadnika);
    }

    public Rezultat spremiPodatkeOKlijentu(KlijentPodatak klijentPodaci, String brojRadnika, List<Relation> nodesForAdd, List<Relation> nodesForUpdate, List<Relation> nodesForDelete) throws ZokException_Exception {
        return _getDescriptor().getProxy().spremiPodatkeOKlijentu(klijentPodaci,brojRadnika,nodesForAdd,nodesForUpdate,nodesForDelete);
    }

    public Rezultat spremiPodatkeOKlijentuOld(KlijentPodatak klijentPodaci, String brojRadnika, List<Organigram> organigramDelete, List<Organigram> organigramUpdate, List<Organigram> organigramAdd) throws ZokException_Exception {
        return _getDescriptor().getProxy().spremiPodatkeOKlijentuOld(klijentPodaci,brojRadnika,organigramDelete,organigramUpdate,organigramAdd);
    }

    public Rezultat spremiKycDatumPotpisa(String idKlijent, String datum, String radnik) {
        return _getDescriptor().getProxy().spremiKycDatumPotpisa(idKlijent,datum,radnik);
    }

    public List<Dokaz> provjeriDokaznice(KlijentPodatak klijentPodaci) throws ZokException_Exception {
        return _getDescriptor().getProxy().provjeriDokaznice(klijentPodaci);
    }

    public Rezultat spremiDokazanePodatke(List<String> dokaz, KlijentPodatak klijentPodaci, String brojRadnika) throws ZokException_Exception {
        return _getDescriptor().getProxy().spremiDokazanePodatke(dokaz,klijentPodaci,brojRadnika);
    }

    public Sifarnici dajSifarnike() throws ZokException_Exception {
        return _getDescriptor().getProxy().dajSifarnike();
    }

    public List<SifarnikC> dajListuSegmenata(String oznTabele, String oj) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuSegmenata(oznTabele,oj);
    }

    public List<SifarnikC> dajListuSegOznaka(String oznTabele, String oj, String segment) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuSegOznaka(oznTabele,oj,segment);
    }

    public List<SifarnikC> dajListuVccCode(String segment) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuVccCode(segment);
    }

    public List<SifarnikC> dajListuRadnikaOj(String oj) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuRadnikaOj(oj);
    }

    public List<SifarnikC> dajListuStvarniVlasnik(String podtip) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuStvarniVlasnik(podtip);
    }

    public List<SifarnikC> dajListuSektorNere(String sektor) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuSektorNere(sektor);
    }

    public List<KlijentLista> dajKlijentListu(String id, String vrijednost) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajKlijentListu(id,vrijednost);
    }

    public CorporateBOMDTO dajKlijenta(String idKlijenta) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajKlijenta(idKlijenta);
    }

    public List<PodatakPodtip> dajListuPolja(String podtip) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuPolja(podtip);
    }

    public List<PodatakPodtip> dajListuPoljaProspekt(List<KlijentProspekt> prospekt) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajListuPoljaProspekt(prospekt);
    }

    public List<KlijentLista> dajFizickaOsobaListu(String id, String type) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajFizickaOsobaListu(id,type);
    }

    public FizOsoPodaci dajFizickuOsobu(String jmbg) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajFizickuOsobu(jmbg);
    }

    public byte[] dajKYCUpitnik(KlijentPodatak klijentPodaci, String brojRadnika, byte[] barCode, String brojNaljepnice, List<Relation> arg4) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajKYCUpitnik(klijentPodaci,brojRadnika,barCode,brojNaljepnice,arg4);
    }

    public PdfDocument dajW8BENEPdf(KlijentPodatak klijentPodaci, String brojRadnika) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajW8BENEPdf(klijentPodaci,brojRadnika);
    }

    public PdfDocument dajW9(KlijentPodatak klijentPodaci, String brojRadnika) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajW9(klijentPodaci,brojRadnika);
    }

    public byte[] dajOragnigramPdf(KlijentPodatak klijentPodaci, String brojRadnika, List<OrganigramRelation> barCode) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajOragnigramPdf(klijentPodaci,brojRadnika,barCode);
    }

    public ZokPrint dajZokPrint(KlijentPodatak klijentPodaci, String brojRadnika) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajZokPrint(klijentPodaci,brojRadnika);
    }

    public List<Relation> dajVeze(String idKlijenta, String tip) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajVeze(idKlijenta,tip);
    }

    public PorklIzlaz dajPorklIzlaz(String klijent) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajPorklIzlaz(klijent);
    }

    public KlijentPodatakKyc dajKlijentPodatakKyc(String klijentId) throws ZokException_Exception {
        return _getDescriptor().getProxy().dajKlijentPodatakKyc(klijentId);
    }

}
