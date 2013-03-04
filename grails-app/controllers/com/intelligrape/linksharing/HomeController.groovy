package com.intelligrape.linksharing

class HomeController {
    def userService

    def index = {
        List<Topic> publicTopics = userService.publicTopics
        List<Topic> latestTopics = userService.latestTopics
        List hotTopics = userService.hotTopics
        Map topicsAndSubscriptions = [:]
        hotTopics.each { list ->
            Topic topic = list.first()
            Long count = list.last()
            topicsAndSubscriptions[topic] = count
        }
        [user: userService.currentUser, hotTopics: topicsAndSubscriptions, latestTopics:latestTopics]
    }
}
