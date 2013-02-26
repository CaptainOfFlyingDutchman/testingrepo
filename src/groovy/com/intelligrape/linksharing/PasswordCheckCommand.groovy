package com.intelligrape.linksharing

import grails.validation.Validateable

// TODO-MANVENDRA - Remove unused imports

@Validateable
class PasswordCheckCommand {
    String password
    String confirmPassword
    User user

    static constraints = {
        password blank: false
        confirmPassword blank: false, validator: { val, obj ->
            if (!val.equals(obj.password)) {
                return false
            }
        }
    }
}
