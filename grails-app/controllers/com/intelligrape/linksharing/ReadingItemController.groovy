package com.intelligrape.linksharing

class ReadingItemController {
    def readingItemService
    def userService
    def linkSharingService

    // View is passing Resource.id
    def markRead() {
        ReadingItem readingItem = ReadingItem.findByResourceAndUser(Resource.get(params.id), userService.currentUser)
        readingItem.delete(flush: true)
//        readingItemService.getReadingItem(params.int("id")).delete(flush: true)
        redirect controller: "user", action: "listTopics"
    }

    // View is passing Resource.id
    def markUnread() {
//        ReadingItem readingItem = ReadingItem.findByResourceAndUser(Resource.get(params.id), userService.currentUser)
        linkSharingService.createReadingItem(Resource.get(params.id), userService.currentUser)
        readingItemService.getReadingItem(params.int("id")).delete(flush: true)
//        redirect
    }
}
