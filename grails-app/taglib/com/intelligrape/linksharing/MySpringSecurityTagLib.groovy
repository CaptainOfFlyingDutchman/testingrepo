package com.intelligrape.linksharing

import grails.plugins.springsecurity.*

/**
 * MySpringSecurityTagLib
 * A taglib library provides a set of reusable tags to help rendering the views.
 */
class MySpringSecurityTagLib extends SecurityTagLib {

    def springSecurityService

    static namespace = "ls"

    def userFirstname = {
        User user = springSecurityService.currentUser

        out << user.firstName
    }

}
