package com.intelligrape.linksharing

class UserController {
    def userService
    def linkSharingService

    static defaultAction = "listTopics"
    // TODO-MANVENDRA - Remove unnecessary blank lines.
    def createTopic() {
        List<Visibility> visibilityConstants = Visibility.values()
        List<Seriousness> seriousnessConstants = Seriousness.values()
        User loggedInUser = userService.currentUser
        [visibilityConstants: visibilityConstants, seriousnessConstants: seriousnessConstants, loggedInUser: loggedInUser]
    }

    def saveTopic(SaveTopicCommand command) {
        if (command.validate()) {
            Topic topic = linkSharingService.createTopic(command.visibility, command.name, command.user)
            linkSharingService.createSubscription(command.seriousness, command.user, topic)
        }
        redirect action: "listTopics"
    }

    def listTopics() {
        if (userService.currentUser) {
            User user = userService.currentUser
            List<Topic> ownedTopics = Topic.findAllByOwner(user)
            // TODO-MANVENDRA - please create a method for criteria code below.
            List<Topic> publicTopics = userService.publicTopics
            List<Subscription> subscriptions = Subscription.findAllBySubscriber(user)
            List<ReadingItem> readingItems = userService.readingItems

            if (UserRole.findByUserAndRole(userService.currentUser, Role.adminRole)) {
                List<Resource> allResources = Resource.list()
                List<Topic> allTopics = Topic.list()
                [ownedTopics: ownedTopics, publicTopics: publicTopics, subscriptions: subscriptions, user: user,
                        readingItems: readingItems, allResources: allResources, allTopics: allTopics]

            } else {
                [ownedTopics: ownedTopics, publicTopics: publicTopics, subscriptions: subscriptions, user: user,
                        readingItems: readingItems]
            }
        } else {
            render view: "notSignedIn"
        }
    }

    def unsubscribe() {
        Topic topicForUnsubscribe = Topic.get(params.id)
        // TODO-MANVENDRA - There should not be any business in controller, move method below.
        userService.deleteSubscriptionForTopicAndSubscriber(topicForUnsubscribe, userService.currentUser)
        redirect action: "listTopics"
    }

    def subscribe() {
        Topic topicForSubscription = Topic.get(params.id)
        User subscribingUser = userService.currentUser
        // TODO-MANVENDRA - There should not be any business in controller.
        // TODO-MANVENDRA - There are two redirect and both are redirecting to same action.
        // TODO-MANVENDRA - please fix.
        if (userService.getSubscriptionByTopicAndSubscriber(topicForSubscription, subscribingUser)) {
            flash.message = "You're already subscribed to this topic."
            redirect action: "listTopics"
            return
        }
        linkSharingService.createSubscription(Seriousness.SERIOUS, subscribingUser, topicForSubscription)
        redirect action: "listTopics"
    }

    def sendInvitation() {
        Topic topicToSendInvitation = Topic.get(params.id)
        [topicToSendInvitation: topicToSendInvitation, user: userService.currentUser]
    }

    def sendInvitationMail(CheckInvitationsCommand command) {
        if (command.validate()) {
            List<String> emailsList = command.emails.tokenize(",")
            // TODO-MANVENDRA - Create a method for code below
            userService.sendMail(emailsList, command)
        } else {
            [command: command]
        }
    }

    def invitationReply() {
        // TODO-MANVENDRA - Too many if else, try making it more readable
        Topic topicToSubscribe = Topic.get(params.topic)
        if (topicToSubscribe) {
            TopicInvitation topicInvitation = TopicInvitation.findByTopicAndEmail(topicToSubscribe, params.email)
            if (topicInvitation) {
                User user = User.findByUsername(params.email)
                if (!user) {
                    render view: "notALinkSharingUser"
                } else {
                    topicInvitation.delete(flush: true)
                    redirect action: "subscribe", params: [id: topicToSubscribe.id]
                }
            } else {
                render view: "/register/linkExpired"
            }
        }
    }

    def changeTopicSettings() {
        Topic topicToChangeSettings = Topic.get(params.id)
        User subscriber = userService.currentUser
        Subscription subscription = Subscription.findByTopicAndSubscriber(topicToChangeSettings, subscriber)
        List<Seriousness> seriousenssConstants = Seriousness.values()
        [topicToChangeSettings: topicToChangeSettings, subscriber: subscriber,
                seriousnessConstants: seriousenssConstants, subscription: subscription]
    }

    def saveTopicSettings(SaveTopicSettingsCommand command) {
        // TODO-MANVENDRA - 2 different redirects,fix.
        // TODO-MANVENDRA - And create method for code below,
        if (command.validate()) {
            userService.saveTopicSettings(command)
            redirect action: "listTopics"
            return
        } else {
            flash.message = "Error occurred in updating the subscription settings."
            redirect action: "changeTopicSettings", params: [id: command.topic.id]
            return
        }
    }

    def deleteTopic() {
        Topic.get(params.id).delete(flush: true)
        redirect action: "listTopics"
    }
}

