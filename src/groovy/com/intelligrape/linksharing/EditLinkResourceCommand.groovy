package com.intelligrape.linksharing

import grails.validation.Validateable

@Validateable
class EditLinkResourceCommand {
    LinkResource linkResource
    User creator
}
