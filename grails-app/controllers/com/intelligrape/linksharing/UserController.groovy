package com.intelligrape.linksharing

class UserController {

    def springSecurityService
    def linkSharingService

    static defaultAction = "createTopic"

    def createTopic() {
        List<Visibility> visibilityConstants = Visibility.values()
        List<Seriousness> seriousnessConstants = Seriousness.values()

        User loggedInUser = springSecurityService.currentUser

        [visibilityConstants: visibilityConstants, seriousnessConstants: seriousnessConstants, loggedInUser: loggedInUser]
    }


    def saveTopic(SaveTopicCommand command) {
        if (command.validate()) {
            linkSharingService.createTopic(command.visibility, command.name, command.user)
        }

        redirect action: "listTopics"
    }

    def listTopics() {
        [topics: Topic.list()]
    }
}

