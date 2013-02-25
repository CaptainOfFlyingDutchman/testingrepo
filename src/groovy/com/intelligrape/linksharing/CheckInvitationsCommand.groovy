package com.intelligrape.linksharing

import grails.validation.Validateable

// TODO-MANVENDRA - Remove unnecessary blank lines.

@Validateable
class CheckInvitationsCommand {
    String emails
    User user
    Topic topic

    static constraints = {
        emails validator: { val, obj ->
            List<String> emailsList = val.tokenize(",")

            if (emailsList.find {
                !(new CheckEmailCommand(username: it)).validate()
            }) {
                return false
            }
        }
    }
}
