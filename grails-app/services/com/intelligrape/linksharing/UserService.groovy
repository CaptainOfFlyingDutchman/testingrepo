package com.intelligrape.linksharing

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class UserService {
    def springSecurityService
    def sendAsynchronousMailService
    LinkGenerator grailsLinkGenerator

    static transactional = true

    User getCurrentUser() {
        return springSecurityService.currentUser as User
    }

    def generateTopicInvitation(CheckInvitationsCommand command, String emailId) {
        return new TopicInvitation(topic: command.topic, email: emailId).save(flush: true, failOnError: true)
    }

    def sendInvitationMail(CheckInvitationsCommand command, String emailId) {
        String subjectText = "Invitation"
        String url = grailsLinkGenerator.link(controller: "user", action: "invitationReply", absolute: true,
                params: [email: emailId, topic: command.topic.id])
        String template = "/user/sendInvitationBody"
        Map templateModel = [url: url, topic: command.topic, user: currentUser, email: emailId]
        sendAsynchronousMailService.sendAsynchronousMail(emailId, subjectText, template, templateModel)
    }
}
