package com.intelligrape.linksharing

class ReadingItemController {
    def readingItemService
    def userService
    def linkSharingService

    // View is passing Resource.id
    def markRead() {
        ReadingItem readingItem = readingItemService.getReadingItemByResourceAndUser(params.int("id"))
        Integer topicIdOfThisResourceOfThisReadingItem = readingItem.resource.topic.id
        readingItem.delete(flush: true)
        if (readingItem.resource.className == LinkResource.class.name)
            redirect controller: "resource", action: "viewAssociatedLinkResources", id: topicIdOfThisResourceOfThisReadingItem
        else
            redirect controller: "resource", action: "viewAssociatedDocumentResources", id: topicIdOfThisResourceOfThisReadingItem
    }

    // View is passing Resource.id
    def markUnread() {
        ReadingItem readingItem = linkSharingService.createReadingItem(readingItemService.getResource(params.int("id")),
                userService.currentUser)
        if (readingItem.resource.className == LinkResource.class.name)
            redirect controller: "resource", action: "viewAssociatedLinkResources", id: readingItem.resource.topic.id
        else
            redirect controller: "resource", action: "viewAssociatedDocumentResources", id: readingItem.resource.topic.id
    }
}
