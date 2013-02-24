package com.intelligrape.linksharing

import grails.validation.Validateable

@Validateable
class SaveLinkResourceCommand {
    Topic topic
    User creator
    String url
    String summary
    String title

    static constraints = {
        summary blank: true, nullable: true
        title unique: "topic"
    }
}
