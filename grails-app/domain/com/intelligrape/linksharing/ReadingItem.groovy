package com.intelligrape.linksharing

class ReadingItem {
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User, resource:Resource]

    static mapping = {
    }

    static constraints = {
    }

    @Override
    public String toString() {
        return user.firstName + " " + resource.title;
    }
}
