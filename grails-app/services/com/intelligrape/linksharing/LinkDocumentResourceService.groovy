package com.intelligrape.linksharing

import org.quartz.JobDataMap
import org.quartz.JobDetail

class LinkDocumentResourceService {
    def userService
    def quartzScheduler
    def linkSharingService
    static transactional = true

    boolean deleteLinkResource(LinkResource linkResourceToDelete) {
        if (linkResourceToDelete.creator.equals(userService.currentUser) ||
                linkResourceToDelete.topic.owner.equals(userService.currentUser)) {
            linkResourceToDelete.delete(flush: true)
            return true
        } else {
            return false
        }
    }

    def createReadingItemForCreatorAndAllSubscribers(Resource resource) {
        CreateReadingItemJob.triggerNow(['resource': resource] as Map)
    }

    def createReadingItemForCreatorAndAllSubscriberFromJob(Resource resource) {
        List<Subscription> subscriptionList = Subscription.findAllByTopic(resource.topic)
        subscriptionList.each { Subscription subscription ->
            linkSharingService.createReadingItem(resource, subscription.subscriber)
        }
    }
}
