package hr.foi.diplomski.rad.core.model

import groovy.transform.ToString

@ToString
class RispoResourcesDTO {
	public static WsdlUrlDTO wsdlURL = new WsdlUrlDTO()
  public static String dbSchema
  public static String datasource
  public static String rispoWS_url
  public static String rispoAPP_url
}
