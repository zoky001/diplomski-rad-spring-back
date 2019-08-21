package hr.foi.diplomski.rad.core.command

@groovy.transform.ToString
class GetDataRispoServiceCommand {
    String criteria
    int criteriaType
    String oznDevize
    String datum
    String brRadnika
    boolean dohvatPoPostojecimClanicama
}
