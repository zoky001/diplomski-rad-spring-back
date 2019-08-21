package hr.foi.diplomski.rad.enums

enum SearchType {

	MB('^([0-9]{8})([0-9]{3})?$', 10),
	OIB('^[0-9]{11}$', 4),
	KPO('^([0-9]{3,13})(KPO)?$', 4),
	NAME('^.{3,}$', 12)

	String regex
	int type
	
	SearchType(String regex, int type) {
		this.regex = regex
		this.type = type
	}
}
