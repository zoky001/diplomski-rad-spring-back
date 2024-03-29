//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.sova.client.model.model;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "SOVAserviceControllerDelegate", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SOVAserviceControllerDelegate {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorinikAutorizacijaoData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wSKorisnikAutorizacija", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSKorisnikAutorizacija")
    @ResponseWrapper(localName = "wSKorisnikAutorizacijaResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSKorisnikAutorizacijaResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSKorisnikAutorizacijaRequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSKorisnikAutorizacijaResponse")
    List<WsKorinikAutorizacijaoData> wSKorisnikAutorizacija(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorisnikPOSifraData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wSKorisnikPOSifra", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSKorisnikPOSifra")
    @ResponseWrapper(localName = "wSKorisnikPOSifraResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSKorisnikPOSifraResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSKorisnikPOSifraRequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSKorisnikPOSifraResponse")
    List<WsKorisnikPOSifraData> wSKorisnikPOSifra(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
      @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorisnikAplikacijaData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wSKorisnikAplikacija", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSKorisnikAplikacija")
    @ResponseWrapper(localName = "wSKorisnikAplikacijaResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSKorisnikAplikacijaResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSKorisnikAplikacijaRequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSKorisnikAplikacijaResponse")
    List<WsKorisnikAplikacijaData> wSKorisnikAplikacija(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTest", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.GetTest")
    @ResponseWrapper(localName = "getTestResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.GetTestResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/getTestRequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/getTestResponse")
    String getTest();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsZaposlenikPOData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wSFunkcijaPO", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSFunkcijaPO")
    @ResponseWrapper(localName = "wSFunkcijaPOResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WSFunkcijaPOResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSFunkcijaPORequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wSFunkcijaPOResponse")
    List<WsZaposlenikPOData> wSFunkcijaPO(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        List<WsFunkcijaPoParametri> arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsAplikativneAkcijeData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wsAplikativneAkcije", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsAplikativneAkcije")
    @ResponseWrapper(localName = "wsAplikativneAkcijeResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsAplikativneAkcijeResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsAplikativneAkcijeRequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsAplikativneAkcijeResponse")
    List<WsAplikativneAkcijeData> wsAplikativneAkcije(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsAplikativneAkcijePOData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wsAplikativneAkcijePO", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsAplikativneAkcijePO")
    @ResponseWrapper(localName = "wsAplikativneAkcijePOResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsAplikativneAkcijePOResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsAplikativneAkcijePORequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsAplikativneAkcijePOResponse")
    List<WsAplikativneAkcijePOData> wsAplikativneAkcijePO(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
      @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
      @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorisnikAutorizacijaPOData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wsKorisnikAutorizacijaPO", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisnikAutorizacijaPO")
    @ResponseWrapper(localName = "wsKorisnikAutorizacijaPOResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisnikAutorizacijaPOResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisnikAutorizacijaPORequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisnikAutorizacijaPOResponse")
    List<WsKorisnikAutorizacijaPOData> wsKorisnikAutorizacijaPO(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
      @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
      @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorisnikAutorizacijaPOData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wsKorisnikWidgetPO", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisnikWidgetPO")
    @ResponseWrapper(localName = "wsKorisnikWidgetPOResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisnikWidgetPOResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisnikWidgetPORequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisnikWidgetPOResponse")
    List<WsKorisnikAutorizacijaPOData> wsKorisnikWidgetPO(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
      @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
      @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorisniciFunkcijaPodredjeniData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wsKorisniciFunkcijaPodredjeni", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisniciFunkcijaPodredjeni")
    @ResponseWrapper(localName = "wsKorisniciFunkcijaPodredjeniResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisniciFunkcijaPodredjeniResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisniciFunkcijaPodredjeniRequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisniciFunkcijaPodredjeniResponse")
    List<WsKorisniciFunkcijaPodredjeniData> wsKorisniciFunkcijaPodredjeni(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
      @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<wsClient_SOVAservice.WsKorisnikAutorizacijaPOData>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "wsKorisnikAutorizacijaDirektnaPO", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisnikAutorizacijaDirektnaPO")
    @ResponseWrapper(localName = "wsKorisnikAutorizacijaDirektnaPOResponse", targetNamespace = "http://controller.web.SOVAServices.zaba.hr/", className = "wsClient_SOVAservice.WsKorisnikAutorizacijaDirektnaPOResponse")
    @Action(input = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisnikAutorizacijaDirektnaPORequest", output = "http://controller.web.SOVAServices.zaba.hr/SOVAserviceControllerDelegate/wsKorisnikAutorizacijaDirektnaPOResponse")
    List<WsKorisnikAutorizacijaPOData> wsKorisnikAutorizacijaDirektnaPO(
      @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
      @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
      @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
      @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

}
