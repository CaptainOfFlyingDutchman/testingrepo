package com.intelligrape.linksharing

class ReadingItemController {
    def readingItemService

    // View is passing Resource.id
    def markRead() {
//        ReadingItem.findByResourceAndUser
        readingItemService.getReadingItem(params.int("id")).delete(flush: true)
        redirect controller: "user", action: "listTopics"
    }

    // View is passing Resource.id
    def markUnread() {
        readingItemService.getReadingItem(params.int("id")).delete(flush: true)
//        redirect
    }
}
