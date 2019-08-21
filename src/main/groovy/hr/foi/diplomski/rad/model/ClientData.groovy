package hr.foi.diplomski.rad.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ClientData {
    String uniqueNumber
    String uniqueId
    String registerNumber
    String clientName
    String clientStatus

    boolean isClientActive(){
	return "Y".equals(clientStatus)
    }
}
