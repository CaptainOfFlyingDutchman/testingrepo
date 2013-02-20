package com.intelligrape.linksharing

/**
 * LinkSharingService
 * A service class encapsulates the core business logic of a Grails application
 */
class LinkSharingService {

    static transactional = true

    User createUser(String username, String firstName, String lastName, String password, boolean enabled) {
        User.findByUsername(username) ?: new User(username: username, firstName: firstName,
                lastName: lastName, password: password, enabled: enabled).save(flush: true, failOnError: true)
    }
}
