package com.intelligrape.linksharing

import grails.gsp.PageRenderer
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

/**
 * SendAsynchronousEmailService
 * A service class encapsulates the core business logic of a Grails application
 */
class SendAsynchronousMailService {

    def asynchronousMailService
    PageRenderer groovyPageRenderer
    LinkGenerator grailsLinkGenerator


    static transactional = true


    def sendAsynchronousMail(String email, String subjectText, String template, Map templateModel) {
        asynchronousMailService.sendAsynchronousMail {
            to email
            subject subjectText
            html groovyPageRenderer.render(template: template,
                    model: templateModel)
        }
    }


}
