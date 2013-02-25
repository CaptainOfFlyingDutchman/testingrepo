package com.intelligrape.linksharing

import grails.plugins.springsecurity.*

// TODO-MANVENDRA - Remove unnecessary blank lines.
class LinkSharingSpringSecurityTagLib extends SecurityTagLib {

    def springSecurityService

    static namespace = "ls"

    def userFirstName = {
        User user = springSecurityService.currentUser

        out << user.firstName
    }

}
