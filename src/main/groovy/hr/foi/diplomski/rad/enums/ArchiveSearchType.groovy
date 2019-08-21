package hr.foi.diplomski.rad.enums

enum ArchiveSearchType {

    KPO(2,'^([0-9]{3,13})(KPO)?$'),	MB(4,'^([0-9]{8})([0-9]{3})?$')

    int type
    String regex

    ArchiveSearchType(int type, String regex) {
	this.type = type
	this.regex = regex
    }
}
