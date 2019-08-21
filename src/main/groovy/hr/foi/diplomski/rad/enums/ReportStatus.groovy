package hr.foi.diplomski.rad.enums

enum ReportStatus {

	CREATING(0),
	IN_PROGRESS(1),
	LOCKED(2),
	ERROR(3),
	DENIED(4)

	Integer value

	private ReportStatus(int value) {
		this.value = value
	}

	static ReportStatus valueOf(Integer val){
		if(val==null){
			return null
		}
		for(ReportStatus v:values()){
			if(v.value.equals(val)){
				return v
			}
		}
		throw new IllegalArgumentException("Ne postoji enum sa vrijedno��u "+ val)
	}
}
