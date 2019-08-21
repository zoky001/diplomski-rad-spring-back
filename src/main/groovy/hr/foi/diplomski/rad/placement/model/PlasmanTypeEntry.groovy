package hr.foi.diplomski.rad.placement.model

import groovy.transform.ToString

@ToString
class PlasmanTypeEntry {
    Integer id
    String aplikacija
    String oznakaLimita
    String sifraNamjene
    String tip

    @Override
    String toString() {
	return String.format("{ id:%d, aplikacija:%s oznakaLimita: %s,  sifraNamjene:%s,  tip:%s }", id, aplikacija, oznakaLimita, sifraNamjene, tip)
    }
}
