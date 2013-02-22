package com.intelligrape.linksharing

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class UserController {

    def springSecurityService
    def linkSharingService
    def sendAsynchronousMailService
    LinkGenerator grailsLinkGenerator

    static defaultAction = "createTopic"

    def createTopic() {
        List<Visibility> visibilityConstants = Visibility.values()
        List<Seriousness> seriousnessConstants = Seriousness.values()

        User loggedInUser = springSecurityService.currentUser

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
        if (springSecurityService.currentUser) {
            User user = springSecurityService.currentUser

            List<Topic> ownedTopics = Topic.findAllByOwner(user)

            List<Topic> publicTopics = Topic.createCriteria().list {
                eq("visibility", Visibility.PUBLIC)
            }

            List<Subscription> subscriptions = Subscription.findAllBySubscriber(user)

            [ownedTopics: ownedTopics, publicTopics: publicTopics, subscriptions: subscriptions]
        } else {
            render view: "notSignedIn"
        }

    }

    def unsubscribe() {
        Topic topicForUnsubscribe = Topic.get(params.id)
        Subscription subscription = Subscription.findByTopicAndSubscriber(topicForUnsubscribe, springSecurityService.currentUser as User)
        subscription.delete(flush: true)
        redirect action: "listTopics"
    }

    def subscribe() {
        Topic topicForSubscription = Topic.get(params.id)
        User subscribingUser = springSecurityService.currentUser as User
        if (Subscription.findByTopicAndSubscriber(topicForSubscription, subscribingUser)) {
            flash.message = "You're already subscribed to this topic."
            redirect action: "listTopics"
            return
        }
        linkSharingService.createSubscription(Seriousness.SERIOUS, subscribingUser, topicForSubscription)
        redirect action: "listTopics"
    }

    def sendInvite() {
        Topic topicToSendInvitation = Topic.get(params.id)
        [topicToSendInvitation: topicToSendInvitation, user: springSecurityService.currentUser as User]
    }

    def sendInvitationMail(CheckInvitationsCommand command) {
        if (command.validate()) {
            List<String> emailsList = command.emails.tokenize(",")
            emailsList.each { String emailId ->
                TopicInvitation topicInvitation = new TopicInvitation(topic: command.topic, email: emailId)
                topicInvitation.save(flush: true, failOnError: true)
                String subjectText = "Invitation"
                String url = grailsLinkGenerator.link(controller: "user", action: "invitationReply", absolute: true,
                        params: [email: emailId, topic: command.topic.id])
                String template = "/user/sendInvitationBody"
                Map templateModel = [url: url, topic: command.topic, user: springSecurityService.currentUser as User, email: emailId]

                sendAsynchronousMailService.sendAsynchronousMail(emailId, subjectText, template, templateModel)
            }
        } else {
            [command: command]
        }
    }

    def invitationReply() {
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
        User subscriber = springSecurityService.currentUser as User
        Subscription subscription = Subscription.findByTopicAndSubscriber(topicToChangeSettings, subscriber)
        List<Seriousness> seriousenssConstants = Seriousness.values()
        [topicToChangeSettings: topicToChangeSettings, subscriber: subscriber, seriousnessConstants: seriousenssConstants
        ,subscription:subscription]
    }

    def saveTopicSettings(SaveTopicSettingsCommand command) {
        if (command.validate()) {
            Subscription subscriptionToChange = Subscription.findByTopicAndSubscriber(command.topic, command.user)
            subscriptionToChange.seriousness = command.seriousness
            subscriptionToChange.save(flush: true, failOnError: true)
            redirect action: "listTopics"
            return
        } else {
            flash.message = "Error occurred in updating the subscription settings."
            redirect action: "changeTopicSettings", params: [id: command.topic.id]
            return
        }
    }

}

