package hr.foi.diplomski.rad.group.command

@groovy.transform.ToString
class FindByStatusAndOrganizationalUnitsGroupCommand {

    Integer status
    String date
    Set<String> organizationalUnits
}
