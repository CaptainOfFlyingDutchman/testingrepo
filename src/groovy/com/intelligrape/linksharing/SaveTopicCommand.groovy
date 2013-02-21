package com.intelligrape.linksharing

import grails.validation.Validateable

@Validateable
class SaveTopicCommand {
    Visibility visibility
    String name
    User user
//    Seriousness seriousness
}
