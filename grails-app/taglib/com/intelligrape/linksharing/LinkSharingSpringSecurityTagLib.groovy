package com.intelligrape.linksharing

import grails.plugins.springsecurity.*

class LinkSharingSpringSecurityTagLib extends SecurityTagLib {

    def springSecurityService

    static namespace = "ls"

    def userFirstName = {
        User user = springSecurityService.currentUser

        out << user.firstName
    }

}
