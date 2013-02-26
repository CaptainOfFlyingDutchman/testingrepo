package com.intelligrape.linksharing

import grails.validation.Validateable

// TODO-MANVENDRA - Remove unused imports, and unnecessary blank lines.

@Validateable
class RegisterCommand {
    String username
    String firstName
    String lastName
    String password
    String confirmPassword

    static constraints = {
        username blank: false, unique: true, email: true, validator: { val, obj ->
            if (User.findByUsername(val)) {
                return false
            }
        }
        password blank: false
        firstName blank: false
        lastName nullable: true
        confirmPassword validator: { val, obj ->
            if (!val.equals(obj.password))
                return false
        }
    }

    User registerUser() {
        User user = new User(username: username, firstName: firstName, lastName: lastName, password: password, enabled: true)
        return user
    }
}
