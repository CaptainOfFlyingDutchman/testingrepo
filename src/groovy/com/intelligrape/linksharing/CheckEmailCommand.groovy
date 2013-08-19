package com.intelligrape.linksharing

import grails.validation.Validateable

// TODO-MANVENDRA - Remove unused imports
@Validateable
class CheckEmailCommand {
    String username  //username

    static constraints = {
        username email: true, blank: false
    }
}
