package hr.foi.diplomski.rad.model


class RispoException extends Exception {

    RispoException(String message) {
	super(message)
    }

    RispoException(String message, Exception cause) {
	super(message, cause)
    }
}
