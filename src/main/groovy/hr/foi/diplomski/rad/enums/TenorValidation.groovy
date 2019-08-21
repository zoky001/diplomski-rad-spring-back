package hr.foi.diplomski.rad.enums

enum TenorValidation {

	DATE("[0-9]{2}[.]{1}[0-9]{2}[.]{1}[0-9]{4}[.]?", "U polje Tenor mo�e se unijeti samo datum."), // Dozvoljeno je dd.mm.yyyy i dd.mm.yyyy.
	DATE_STRING("[0-9]{2}[.]{1}[0-9]{2}[.]{1}[0-9]{4}.*", "U polje Tenor prvo se mora unijeti datum, a zatim se mo�e unijeti tekst.")

	String regularExpression
	String errorMessage
	
	TenorValidation(String regularExpression, String errorMessage)  {
		this.regularExpression = regularExpression
		this.errorMessage = errorMessage
	}
}
