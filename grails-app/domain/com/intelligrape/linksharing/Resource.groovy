package com.intelligrape.linksharing

class Resource {
    User creator
    String title
    String summary
    String className

    static hasMany = [readingItems: ReadingItem]
    static belongsTo = [topic: Topic]

    static mapping = {
        className formula: 'CLASS'
    }

    static constraints = {
        title unique: "topic"
        summary nullable: true, blank: true, maxSize: 1024
        className nullable: true
    }

    @Override
    public String toString() {
        return "${title}";
    }
}
