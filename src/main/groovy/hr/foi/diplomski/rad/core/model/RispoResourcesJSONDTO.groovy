package hr.foi.diplomski.rad.core.model

import com.fasterxml.jackson.annotation.JsonProperty

import groovy.transform.ToString

@ToString
class RispoResourcesJSONDTO {

    @JsonProperty(value="wsdlURL")
    WsdlUrlDTO wsdlURL = new WsdlUrlDTO()

    String dbSchema
    String datasource
    String rispoWS_url
    String rispoAPP_url
    String tecajDevize_url
}
