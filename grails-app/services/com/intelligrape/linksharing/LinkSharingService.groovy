package com.intelligrape.linksharing

class LinkSharingService {

    static transactional = true

    User createUser(String username, String firstName, String lastName, String password, boolean enabled) {
        User.findByUsername(username) ?: new User(username: username, firstName: firstName,
                lastName: lastName, password: password, enabled: enabled).save(flush: true, failOnError: true)
    }

    Topic createTopic(Visibility visibility, String name, User owner) {
        new Topic(visibility: visibility, name: name, owner: owner).save(flush: true, failOnError: true)
    }


}
