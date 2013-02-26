package com.intelligrape.linksharing

import grails.gsp.PageRenderer

// TODO-MANVENDRA - Remove unnecessary blank lines.
class SendAsynchronousMailService {
    def asynchronousMailService
    PageRenderer groovyPageRenderer

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
