package com.intelligrape.linksharing

import grails.gsp.PageRenderer
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

/**
 * SendAsynchronousEmailService
 * A service class encapsulates the core business logic of a Grails application
 */
class SendAsynchronousEmailService {

    def asynchronousMailService
    PageRenderer groovyPageRenderer
    LinkGenerator grailsLinkGenerator


    static transactional = true

    def sendAsyncEmail(User user, ForgotPasswordToken forgotPasswordToken) {
        String url = grailsLinkGenerator.link(controller: 'register', action: 'resetPassword', absolute: true,
                params: [forgotPasswordToken: forgotPasswordToken.token] )

        asynchronousMailService.sendAsynchronousMail {
            to user.username
            subject "Your password"
            html groovyPageRenderer.render(template: "/register/forgotPasswordBody",
                    model: [user: user, url: url])
        }
    }
}
