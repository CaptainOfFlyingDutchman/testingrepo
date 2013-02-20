package com.intelligrape.linksharing

import com.intelligrape.linksharing.*
import grails.validation.Validateable

@Validateable
class CheckEmailCommand {
    String username

    static constraints = {
        username email: true, blank: false
    }
}
