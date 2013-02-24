package com.intelligrape.linksharing

import grails.validation.Validateable

@Validateable
class SaveNewChangesToLinkResourceCommand {
    String title
    String summary
    String url
    User creator
    String id
}
