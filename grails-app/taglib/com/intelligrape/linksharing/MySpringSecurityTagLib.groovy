package com.intelligrape.linksharing

import grails.plugins.springsecurity.*

/**
 * MySpringSecurityTagLib
 * A taglib library provides a set of reusable tags to help rendering the views.
 */

// TODO :  Not a good name for class, do change it MySpringSecurityTagLib

class MySpringSecurityTagLib extends SecurityTagLib {

    def springSecurityService

    static namespace = "ls"

    // TODO :  fix its casing userFirstname

    def userFirstname = {
        User user = springSecurityService.currentUser

        out << user.firstName
    }

}
