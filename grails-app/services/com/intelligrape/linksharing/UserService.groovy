package com.intelligrape.linksharing

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class UserService {
    def springSecurityService
    def sendAsynchronousMailService
    LinkGenerator grailsLinkGenerator

    static transactional = true

    User getCurrentUser() {
        return springSecurityService.currentUser as User
    }

    def sendMail(List<String> emailsList, CheckInvitationsCommand command) {
        emailsList.each { String emailId ->
            generateTopicInvitation(command, emailId)
            sendInvitationMail(command, emailId)
        }
    }

    TopicInvitation generateTopicInvitation(CheckInvitationsCommand command, String emailId) {
        return new TopicInvitation(topic: command.topic, email: emailId).save(flush: true, failOnError: true)
    }

    def sendInvitationMail(CheckInvitationsCommand command, String emailId) {
        String subjectText = "Invitation"
        String url = grailsLinkGenerator.link(controller: "user", action: "invitationReply", absolute: true,
                params: [email: emailId, topic: command.topic.id])
        String template = "/user/sendInvitationBody"
        Map templateModel = [url: url, topic: command.topic, user: currentUser, email: emailId]
        sendAsynchronousMailService.sendAsynchronousMail(emailId, subjectText, template, templateModel)
    }

    List<Topic> getPublicTopics() {
        List<Topic> publicTopics = Topic.createCriteria().list {
            eq("visibility", Visibility.PUBLIC)
        }
        return publicTopics
    }

    List<Topic> getLatestTopics() {
        List<Topic> latestTopics = Topic.createCriteria().list {
            order("dateCreated", "desc")
        }
    }

    // This will return a list which contains another list each containing Topic, and an integer
    List getHotTopics() {
        List hotTopics = Topic.createCriteria().list {
            projections {
                subscriptions {
                    groupProperty('topic')
                    count("topic", "count")
                }
            }
            order("count", "desc")
        }
        return hotTopics
    }

    List<ReadingItem> getReadingItems() {
        return ReadingItem.findAllByUser(currentUser)
    }

    def deleteSubscriptionForTopicAndSubscriber(Topic forTopic, User subscriber) {
        getSubscriptionByTopicAndSubscriber(forTopic, subscriber).delete(flush: true)
    }

    Subscription getSubscriptionByTopicAndSubscriber(Topic topic, User subscriber) {
        return Subscription.findByTopicAndSubscriber(topic, subscriber)
    }

    def saveTopicSettings(SaveTopicSettingsCommand command) {
        Subscription subscriptionToChange = getSubscriptionByTopicAndSubscriber(command.topic, command.user)
        subscriptionToChange.seriousness = command.seriousness
        subscriptionToChange.save(flush: true, failOnError: true)
    }
}
