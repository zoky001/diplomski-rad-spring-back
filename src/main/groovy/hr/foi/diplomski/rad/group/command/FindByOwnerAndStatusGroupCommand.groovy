package hr.foi.diplomski.rad.group.command

import hr.foi.diplomski.rad.enums.ReportStatus

@groovy.transform.ToString
class FindByOwnerAndStatusGroupCommand {

    String brRadnika 
    ReportStatus status
}
