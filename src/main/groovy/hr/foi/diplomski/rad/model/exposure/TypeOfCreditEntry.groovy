package hr.foi.diplomski.rad.model.exposure

import groovy.transform.ToString

@ToString
class TypeOfCreditEntry {
    Integer id
    String aplikacija
    String vrstaOznakaPosla
    String kategorija
    String sifraNamjene
    String oznakaVrstePlasmana
    String nacinKoristenja
    int poredak

    @Override
    String toString() {
	return String.format("{ id:%d, aplikacija:%s vop: %s, kategorija:%s, sifraNamjene:%s, nacinKoristenja: %s, oznakaVrstePlasmana:%s }", id,
		aplikacija, vrstaOznakaPosla, kategorija, sifraNamjene, nacinKoristenja, oznakaVrstePlasmana)
    }
}
