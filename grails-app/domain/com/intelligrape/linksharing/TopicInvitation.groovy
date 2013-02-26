package com.intelligrape.linksharing

class TopicInvitation {
    Topic topic
    String email

    static mapping = {
    }

    static constraints = {
        email email: true, blank: false
        topic nullable: false
    }
}
