package hr.foi.diplomski.rad.group.command

import org.joda.time.DateTime

@groovy.transform.ToString
class FindByStatusAndDateGroupCommand {

    int status
    DateTime date
}
