package com.intelligrape.linksharing

class Resource {
    User creator
    String title
    String summary
    boolean isRead

    static belongsTo = [topic: Topic]

    static mapping = {
    }

    static constraints = {
        title unique: "topic"
        summary maxSize: 1024, blank: true, nullable: true
    }

    @Override
    public String toString() {
        return "${title}";
    }
}
