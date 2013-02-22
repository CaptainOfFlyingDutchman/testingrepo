package com.intelligrape.linksharing

class LinkSharingService {

    static transactional = true

    User createUser(String username, String firstName, String lastName, String password, boolean enabled) {
        User.findByUsername(username) ?: new User(username: username, firstName: firstName,
                lastName: lastName, password: password, enabled: enabled).save(flush: true, failOnError: true)
    }

    Topic createTopic(Visibility visibility, String name, User owner) {
        return new Topic(visibility: visibility, name: name, owner: owner).save(flush: true, failOnError: true)
    }

    Subscription createSubscription(Seriousness seriousness, User subscriber, Topic topic) {
        return new Subscription(seriousness: seriousness, subscriber: subscriber, topic: topic).save(flush: true, failOnError: true)
    }
}
