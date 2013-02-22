package com.intelligrape.linksharing

import grails.validation.Validateable

@Validateable
class SaveTopicSettingsCommand {
    Seriousness seriousness
    User user
    Topic topic
}
