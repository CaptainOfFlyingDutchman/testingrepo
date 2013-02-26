package com.intelligrape.linksharing

import grails.validation.Validateable

@Validateable
class SaveDocumentResourceCommand {
    Topic topic
    User creator
    String fileName
    String summary
    String title

    static constraints = {
        summary blank: true, nullable: true
        title unique: "topic"
        fileName nullable: false
    }
}
