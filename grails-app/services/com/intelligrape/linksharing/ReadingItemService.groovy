package com.intelligrape.linksharing

class ReadingItemService {
    static transactional = true

    ReadingItem getReadingItem(Integer id) {
        return ReadingItem.get(id)
    }
}
