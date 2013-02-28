package com.intelligrape.linksharing

class ReadingItemService {
    def userService

    static transactional = true

    ReadingItem getReadingItem(Integer id) {
        return ReadingItem.get(id)
    }

    Resource getResource(Integer id) {
        return Resource.get(id)
    }

    ReadingItem getReadingItemByResourceAndUser(Integer id) {
        return ReadingItem.findByResourceAndUser(getResource(id),
                userService.currentUser)
    }
}
