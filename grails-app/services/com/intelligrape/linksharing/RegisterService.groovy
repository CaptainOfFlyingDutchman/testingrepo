package com.intelligrape.linksharing

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class RegisterService {
    def springSecurityService
    def sendAsynchronousMailService
    LinkGenerator grailsLinkGenerator

    static transactional = true

    def reauthenticate(String username, String password) {
        springSecurityService.reauthenticate(username, password)
    }

    ForgotPasswordToken generateForgotPasswordToken(User forUser) {
        String uuid = UUID.randomUUID().toString()
        return new ForgotPasswordToken(token: uuid, user: forUser).save(flush: true, failOnError: true)
    }

    def sendForgotPasswordMail(User toUser, ForgotPasswordToken forgotPasswordToken) {
        String subjectText = "Your password"
        String url = grailsLinkGenerator.link(controller: 'register', action: 'resetPassword', absolute: true,
                base: "http://linksharing-manvendra.qa3.intelligrape.net",
                params: [forgotPasswordToken: forgotPasswordToken.token])
        String template = '/register/forgotPasswordBody'
        Map templateModel = [user: toUser, url: url]
        sendAsynchronousMailService.sendAsynchronousMail(toUser.username, subjectText, template, templateModel)
    }
}
